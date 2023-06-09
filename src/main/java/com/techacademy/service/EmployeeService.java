package com.techacademy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service

public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** Employeeを1件検索して返す */
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Employeeの登録を行なう  */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        /**パスワード暗号化  */
        employee.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));
        return employeeRepository.save(employee);
    }

    /** ペジネーション  */
    public Page<Employee> getCountEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}