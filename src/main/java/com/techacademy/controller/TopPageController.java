package com.techacademy.controller;

import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopPageController {
    private final ReportService service;

    public TopPageController(ReportService service) {
        this.service = service;
    }

    /** トップページ画面を表示 */
    @GetMapping("/")
    public String getTopPage(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        // ログインをした社員の日報の検索結果をModelに登録
        Employee detailEmployee = userDetail.getUser();
        model.addAttribute("reportlist", service.findByEmployee(detailEmployee));
        // ログインをした社員の日報の検索数をModelに登録
        List<Report> report = service.getReportList();
        long num = report.stream().filter(value-> value.getEmployee().getId().equals(userDetail.getUser().getId())).count();
        model.addAttribute("reportcount", num);
        // toppage.htmlに画面遷移
        return "toppage";
    }
}