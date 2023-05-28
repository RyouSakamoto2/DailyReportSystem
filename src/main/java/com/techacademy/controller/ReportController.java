package com.techacademy.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")

public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetail userDetail, Model model, Pageable pageable) {
        // 全件検索結果をModelに登録
        model.addAttribute("reportlist", service.getReportList());
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        // ページ情報をModelに登録
        Page<Report> page = service.getCountReport(pageable);
        model.addAttribute("totalItems", page.getTotalElements());
        // employee/list.htmlに画面遷移
        return "report/list";
    }

    /** Report登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail userDetail, @ModelAttribute Report reports, Model model) {
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        // Employee登録画面に遷移
        return "report/register";
    }

    /** Report登録処理 */
    @PostMapping("/register")
    public String postRegister(@AuthenticationPrincipal UserDetail userDetail, @Validated Report report, BindingResult res, Model model) {
        if (res.hasErrors()) {
            // エラーあり
            return getRegister(userDetail, report, model);
        }
        // Report登録
        Employee employee = userDetail.getUser();
        report.setEmployee(employee);
        service.saveReport(report);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }

    /** Report詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String showDetail(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("id") Integer id, Model model) {
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        //UserDetailからログインユーザの社員番号情報を取得
        int employeeId = userDetail.getUser().getId();
        model.addAttribute("employeeId", employeeId);
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        // Report詳細画面に遷移
        return "report/detail";
    }

    /** Report更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("id") Integer id, Model model) {
        //UserDetailからログインユーザの名前情報を取得
        String employeeName = userDetail.getUser().getName();
        model.addAttribute("employeeName", employeeName);
        // 日報情報をModelに登録
        model.addAttribute("report", service.getReport(id));
        // Employee更新画面に遷移
        return "report/update";
    }

    /** Report更新処理 */
    @PostMapping("/update/{id}/")
    public String postReport(@PathVariable("id") Integer id, Report report) {
        // DBからreportを呼び出し、変数databaseRepを宣言、画面から得た情報を取得
        Report databaseRep = service.getReport(id);

        databaseRep.getEmployee().getName();
        databaseRep.setReportDate(report.getReportDate());
        databaseRep.setTitle(report.getTitle());
        databaseRep.setContent(report.getContent());
        databaseRep.setUpdatedAt(report.getUpdatedAt());
        service.saveReport(databaseRep);
        // 一覧画面にリダイレクト
        return "redirect:/report/list";
    }
}