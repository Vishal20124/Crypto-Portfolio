package com.bridgelabz.controller;

import com.bridgelabz.entity.User;
import com.bridgelabz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{username}/roles/{role}")
    public User assignRole(@PathVariable String username, @PathVariable String role) {
        return userService.assignRole(username, role);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByName(username);
    }
}

