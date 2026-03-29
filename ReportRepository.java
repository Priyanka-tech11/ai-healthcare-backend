package ai.healthcare.example.healthcare.repository;

import ai.healthcare.example.healthcare.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByUserEmailOrderByCreatedAtDesc(String userEmail);
}