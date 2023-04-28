package com.laborantproject.laborantproject.service.Impl;

import com.laborantproject.laborantproject.model.dto.request.ReportRequest;
import com.laborantproject.laborantproject.model.dto.response.ReportResponse;
import com.laborantproject.laborantproject.model.entity.Attachment;
import com.laborantproject.laborantproject.model.entity.Laboratorian;
import com.laborantproject.laborantproject.model.entity.Patient;
import com.laborantproject.laborantproject.model.entity.Report;
import com.laborantproject.laborantproject.repository.ReportRepository;
import com.laborantproject.laborantproject.service.LaboratorianService;
import com.laborantproject.laborantproject.service.PatientService;
import com.laborantproject.laborantproject.service.ReportService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    @Autowired
    private final LaboratorianService laboratorianService;
    @Autowired
    private final PatientService patientService;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Page<ReportResponse> getByKeyword(String keyword, Pageable pageable) {
        Page<Report> reports = reportRepository.findByKeyword(keyword, pageable);
        Page<ReportResponse> reportResponses = reports.map(report -> modelMapper.map(report, ReportResponse.class));
        return reportResponses;

    }

    @Override
    public ReportRequest save(ReportRequest reportRequest) {
        laboratorianService.findLabPersonalById(reportRequest.getLabIdNo());
        patientService.findPatientById(reportRequest.getPatientId());
        Report report = modelMapper.map(reportRequest, Report.class);
        report.setPatient(Patient.builder().patientId(reportRequest.getPatientId()).build());
        report.setLaboratorian(Laboratorian.builder().labIdNo(reportRequest.getLabIdNo()).build());
        report.setAttachmentId(reportRequest.getAttachmentId());
        report.setDate(new Date());
        try{
            report=reportRepository.save(report);
        }catch (Exception e){
            System.out.println("kaydedemedim.");
        }

        return modelMapper.map(report, ReportRequest.class);

    }

    @Override
    public List<ReportRequest> getReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportRequest> dtos = reports.stream()
                .map(report -> modelMapper.map(report, ReportRequest.class))
                .collect(Collectors.toList());
        return dtos;
    }


    @Override
    public ReportRequest findReportById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if(report.isPresent()){
            ReportRequest reportRequest = modelMapper.map(report.get(), ReportRequest.class);
            reportRequest.setLabIdNo(report.get().getLaboratorian().getLabIdNo());
            reportRequest.setPatientId(report.get().getPatient().getPatientId());
            reportRequest.setAttachmentId(report.get().getAttachmentId());
            return reportRequest;

        }
        return null;
    }

    @Override
    public Boolean deleteReport(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if(report.isPresent()){
            reportRepository.deleteById(id);
            return true;
        }

        return false;
    }

     @Override
     public List<ReportResponse> getBySorted() {
     List<Report> reports = reportRepository.findAllByOrderByDateDesc();
     List<ReportResponse> reportResponses = reports.stream().map(report -> modelMapper.
     map(report,ReportResponse.class)).collect(Collectors.toList());
     return reportResponses;
     }



}
