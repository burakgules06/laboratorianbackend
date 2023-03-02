package com.laborantproject.laborantproject.repository;

import com.laborantproject.laborantproject.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
