package com.bridgelabz.controller;

import com.bridgelabz.dto.AuthRequest;
import com.bridgelabz.entity.User;
import com.bridgelabz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.bridgelabz.exception.CustomExceptions.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody AuthRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new BadRequestException("Email must not be empty");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new BadRequestException("Password must not be empty");
        }

        return userService.register(request);
    }
}
