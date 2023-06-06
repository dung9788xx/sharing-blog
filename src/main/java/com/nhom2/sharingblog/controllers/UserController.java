package com.nhom2.sharingblog.controllers;

import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dungtv
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "id", required = false) Integer id) {
        User user = userService.getUserById(id);
        return user != null  ? user.getName() : "Not found";
    }

}
