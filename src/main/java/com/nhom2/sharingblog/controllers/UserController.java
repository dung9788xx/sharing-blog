package com.nhom2.sharingblog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dungtv
 */
@RestController
public class UserController {
 @RequestMapping("/login")
public String login( @RequestParam(value = "age", required = false) Integer age,
        @RequestParam(value = "name", required = false) String name) {

    return "hello word";
}

}
