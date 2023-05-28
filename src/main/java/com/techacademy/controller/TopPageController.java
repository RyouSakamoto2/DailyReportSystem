package com.techacademy.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.service.UserDetail;

@Controller
public class TopPageController {
    /** トップページ画面を表示 */
    @GetMapping("/")
    public String getTopPage(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        // toppage.htmlに画面遷移
        return "toppage";
    }
}