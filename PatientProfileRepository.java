package ai.healthcare.example.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ai.healthcare.example.healthcare.entity.PatientProfile;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {}