package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopPageController {
    /** トップページ画面を表示 */
    @GetMapping({"/", "/toppage"})
    public String getTopPage() {
        // toppage.htmlに画面遷移
        return "toppage";
    }
}