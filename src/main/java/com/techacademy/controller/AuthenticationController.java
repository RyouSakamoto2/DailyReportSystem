package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.techacademy.entity.Authentication;
import com.techacademy.service.AuthenticationService;

@Controller

public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    /** Authentication登録処理 */
    @PostMapping("/register")
    public String postRegisterA(Authentication authentication) {
        // Authentication登録
        service.saveAuthentication(authentication);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}