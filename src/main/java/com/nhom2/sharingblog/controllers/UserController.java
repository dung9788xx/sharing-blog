package com.nhom2.sharingblog.controllers;

import com.nhom2.sharingblog.config.JwtUtils;
import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.request.AuthenticationRequest;
import com.nhom2.sharingblog.services.interfaces.UserService;
import com.nhom2.sharingblog.userSecurity.JpaUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author dungtv
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final JpaUserDetailsService jpaUserDetailsService;
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final UserDetails user = jpaUserDetailsService.loadUserByUsername(request.getUsername());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                return ResponseEntity.ok(jwt);
            }
            return ResponseEntity.status(400).body("Error authenticating");
        } catch (Exception e) {
            System.out.println(e);
           return ResponseEntity.status(400).body("" + e.getMessage());
        }
    }

    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

}
