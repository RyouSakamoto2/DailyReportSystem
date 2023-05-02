package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/", "/dailyreport" })

public class DailyReportController {

    // 本来、以下はLoginControllerで記載されるべきだが仮置き
    @GetMapping({ "/", "/login" })
    public String getLogin() {
    // login.htmlに画面遷移
        return "login";
    }

    @GetMapping("/dailyreport")
    public String getToppage() {
    // list.htmlに画面遷移
        return "list";
    }

}