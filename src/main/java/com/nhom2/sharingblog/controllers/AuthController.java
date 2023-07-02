package com.nhom2.sharingblog.controllers;

import com.nhom2.sharingblog.DTO.Auth.UserDTO;
import com.nhom2.sharingblog.common.APIResponse;
import com.nhom2.sharingblog.config.JwtUtils;
import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.request.AuthenticationRequest;
import com.nhom2.sharingblog.services.interfaces.UserService;
import com.nhom2.sharingblog.userSecurity.JpaUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final JpaUserDetailsService jpaUserDetailsService;

    @PostMapping("/login")
    public APIResponse authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final UserDetails user = jpaUserDetailsService.loadUserByUsername(request.getUsername());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                return new APIResponse(HttpStatus.OK, jwt);
            }
            return new APIResponse(HttpStatus.UNAUTHORIZED, "Error authenticating");
        } catch (Exception e) {
            return new APIResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/profile")
    public APIResponse getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(userDetails.getUsername());
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
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
