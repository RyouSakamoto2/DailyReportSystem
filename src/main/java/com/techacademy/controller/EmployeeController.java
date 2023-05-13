package com.techacademy.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model, Pageable pageable) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // ページ情報をModelに登録
        Page<Employee> page = service.getCountEmployee(pageable);
        model.addAttribute("totalItems", page.getTotalElements());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if (res.hasErrors()) {
            // エラーあり
            return getRegister(employee);
        }
        // Employee登録
        employee.getAuthentication().setEmployee(employee);
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee詳細画面を表示 */
    @GetMapping("/detail/{id}/")
    public String showDetail(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // Employee詳細画面に遷移
        return "employee/detail";
    }

    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // Employee更新画面に遷移
        return "employee/update";
    }

    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, Employee employee, Authentication authentication) {
        // DBからEmployeeを呼び出し、変数databaseEmpを宣言、画面から得た情報を取得
        Employee databaseEmp = service.getEmployee(id);
        databaseEmp.setName(employee.getName());
        databaseEmp.getAuthentication().setPassword(employee.getAuthentication().getPassword());
        databaseEmp.getAuthentication().setRole(employee.getAuthentication().getRole());
        databaseEmp.setUpdatedAt(employee.getUpdatedAt());
        service.saveEmployee(databaseEmp);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee削除処理 */
    @GetMapping("/delete/{id}/")
    public String deleteEmployee(@PathVariable("id") Integer id, Employee employee, Authentication authentication) {
        // DBからEmployeeを呼び出し、変数databaseEmpを宣言、画面から得た情報を取得
        // Employeeのdelete_flagに１を立てる
        Employee deleteEmp = service.getEmployee(id);
        deleteEmp.setDelete_flag(1);
        service.saveEmployee(deleteEmp);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

}