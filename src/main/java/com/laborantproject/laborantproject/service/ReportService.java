package com.laborantproject.laborantproject.service;

import com.laborantproject.laborantproject.model.dto.request.ReportRequest;
import com.laborantproject.laborantproject.model.dto.response.ReportResponse;
import com.laborantproject.laborantproject.model.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {
    Page<ReportResponse> getByKeyword(String keyword, Pageable pageable);
    ReportRequest save(ReportRequest reportRequest);
    List<ReportRequest> getReports();
    ReportRequest findReportById(Long id);
    Boolean deleteReport(Long id);
    List<ReportResponse> getBySorted();
}
