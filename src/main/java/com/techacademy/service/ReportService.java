package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service

public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }

    /** Reportを1件検索して返す */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }
    /** ログイン社員のみの日報を検索して返す  */
    public List<Report> findByEmployee(Employee employee){
        return reportRepository.findByEmployee(employee);
    }

    /** Reportの登録を行なう  */
    @Transactional
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    /** ログイン社員のみの日報の件数をカウント */
    public Long getCountReportById(Long id){
        return reportRepository.countById(id);
    }
    /** ペジネーション(全件)  */
    public Page<Report> getCountReport(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

}