package ai.healthcare.example.healthcare.controller;

import ai.healthcare.example.healthcare.entity.Report;
import ai.healthcare.example.healthcare.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/save")
    public Report saveReport(@RequestBody Report report) {

        report.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return reportRepository.save(report);
    }

    @GetMapping("/history/{email}")
    public List<Report> getUserHistory(@PathVariable String email) {

        return reportRepository.findByUserEmailOrderByCreatedAtDesc(email);
    }
}