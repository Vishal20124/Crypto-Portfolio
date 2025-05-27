package com.bridgelabz.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String hello() {
        return "Crypto Portfolio App is running!";
    }
}
