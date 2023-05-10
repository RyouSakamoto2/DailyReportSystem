package com.techacademy.controller;

import org.springframework.stereotype.Controller;

import com.techacademy.service.AuthenticationService;

@Controller

public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
}