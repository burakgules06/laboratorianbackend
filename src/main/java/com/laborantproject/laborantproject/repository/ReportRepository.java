package com.laborantproject.laborantproject.repository;

import com.laborantproject.laborantproject.model.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query("SELECT r FROM Report r JOIN r.patient p JOIN r.laboratorian l WHERE p.name LIKE %:keyword% OR p.surname LIKE %:keyword% OR p.tcNo LIKE %:keyword% OR l.name LIKE %:keyword%")
    Page<Report> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    List<Report> findAllByOrderByDateDesc();

}
