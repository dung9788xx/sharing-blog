package com.nhom2.sharingblog.controllers;

import com.nhom2.sharingblog.common.APIResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/admin")
public class AdminController extends BaseController{
    @GetMapping("/test")
    public APIResponse logout(Authentication authentication) {
        return new APIResponse("Your are admin");
    }
}
