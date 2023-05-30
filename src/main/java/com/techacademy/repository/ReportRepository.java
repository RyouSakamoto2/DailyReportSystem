package com.techacademy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    public Page<Report> findAll(Pageable pageable);
    public Long countById(Long id);
    public List<Report> findByEmployee(Employee employee);
    public Optional<Report> findById(Integer id);
}