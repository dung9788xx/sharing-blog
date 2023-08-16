package com.nhom2.sharingblog.controllers;

import com.nhom2.sharingblog.DTO.Auth.UserDTO;
import com.nhom2.sharingblog.DTO.UpdateProfileDTO;
import com.nhom2.sharingblog.common.APIResponse;
import com.nhom2.sharingblog.common.HttpRequest;
import com.nhom2.sharingblog.common.HttpResponse;
import com.nhom2.sharingblog.config.JwtUtils;
import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.request.AuthenticationRequest;
import com.nhom2.sharingblog.services.AmazonClient;
import com.nhom2.sharingblog.services.interfaces.UserService;
import com.nhom2.sharingblog.userSecurity.JpaUserDetailsService;
import com.nhom2.sharingblog.userSecurity.UserSecurity;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dungtv
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AmazonClient amazonClient;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final JpaUserDetailsService jpaUserDetailsService;
    @PostMapping("/login")
    public APIResponse authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {

        try {
            HttpResponse httpResponse = HttpRequest.sendGet("https://oauth2.googleapis.com/tokeninfo?id_token=" + request.getCredential());
            if (httpResponse.getStatus() != HttpStatus.OK) {
                return new APIResponse(HttpStatus.UNAUTHORIZED, "Error authenticating");
            }
            JSONObject responseJson = httpResponse.getJson();
            UserDetails user = jpaUserDetailsService.loadUserByUsername(responseJson.getString("email"));
            if (user == null) {
                User newUser = new User();
                newUser.setEmail(responseJson.getString("email"));
                newUser.setAvatar(responseJson.getString("picture"));
                newUser.setName(responseJson.getString("name"));
                newUser.setName(responseJson.getString("given_name"));
                userService.save(newUser);
                user = new UserSecurity(newUser);

            }
            String jwt = jwtUtils.generateToken(user);
            return new APIResponse(HttpStatus.OK, jwt);
        } catch (Exception e) {
            return new APIResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/profile")
    public APIResponse getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByEmail(userDetails.getUsername());
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return new APIResponse(userResponse);
    }

    @PostMapping("/profile")
    public APIResponse updateUser(@Valid UpdateProfileDTO updateProfileDTO,@RequestPart(value = "file") MultipartFile multipartFile) {
        User user = userService.updateProfile(updateProfileDTO);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        this.amazonClient.uploadFile(multipartFile);
        return new APIResponse(userResponse);
    }

    @PostMapping("/logout")
    public APIResponse logout(Authentication authentication, @RequestHeader (name="Authorization") String BearerToken) {
        String token = BearerToken.substring(7);
        long tokenTTL = jwtUtils.extractExpiration(token).getTime()-System.currentTimeMillis();
        redisTemplate.opsForValue().set(token, 1, tokenTTL, TimeUnit.MILLISECONDS);
        return new APIResponse("Logout Success");
    }
}
