package com.laborantproject.laborantproject.controller;

import com.laborantproject.laborantproject.model.dto.request.ReportRequest;
import com.laborantproject.laborantproject.model.dto.response.ReportResponse;
import com.laborantproject.laborantproject.model.entity.Attachment;
import com.laborantproject.laborantproject.service.AttachmentService;
import com.laborantproject.laborantproject.service.LaboratorianService;
import com.laborantproject.laborantproject.service.PatientService;
import com.laborantproject.laborantproject.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/report")
@CrossOrigin("*")

public class ReportController {
    private final ReportService reportService;
    private final LaboratorianService laboratorianService;
    private final PatientService patientService;
    private AttachmentService attachmentService;


    @PostMapping("/new")
    public ResponseEntity<ReportRequest> createReport(@ModelAttribute ReportRequest reportRequest,
                                                      @RequestParam("file")MultipartFile file) throws Exception {
    Attachment attachment = null;
    String downloadUrl = "";
        if(reportRequest.getLabIdNo()!=null || reportRequest.getPatientId()!=null){
            try{
                var labIdNoVal = laboratorianService.findLabPersonalById(reportRequest.getLabIdNo());
                var patientIdVal = patientService.findPatientById(reportRequest.getPatientId());
                reportRequest.setLabIdNo(labIdNoVal.getLabIdNo());
                reportRequest.setPatientId(patientIdVal.getPatientId());
            }catch (Exception e){
                //yanlış req
            }
        }
        attachment = attachmentService.saveAttachment(file);
        reportRequest.setAttachmentId(attachment.getId());
        ReportRequest resultReport = reportService.save(reportRequest);
        return ResponseEntity.ok(resultReport);
    }

     @GetMapping("/findAll/sorted")
     public ResponseEntity<List<ReportResponse>> findBySorted(){
     List<ReportResponse> reportResponses = reportService.getBySorted();
     return ResponseEntity.ok(reportResponses);
     }


    @GetMapping("/findById/{fileNo}")
    public ResponseEntity<ReportRequest> findReportById(@PathVariable("fileNo") Long id){

        ReportRequest resultReport = reportService.findReportById(id);
            return ResponseEntity.ok(resultReport);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ReportRequest> updateReport(@PathVariable("id") Long id,
                                                      @RequestBody ReportRequest reportRequest){
        ReportRequest resultDto = reportService.findReportById(id);
        resultDto.setDiagnosis(reportRequest.getDiagnosis());
        resultDto.setPatientId(reportRequest.getPatientId());
        resultDto.setDate(reportRequest.getDate());
        resultDto.setDiagnosisTitle(reportRequest.getDiagnosisTitle());
        resultDto.setLabIdNo(reportRequest.getLabIdNo());
        reportService.save(resultDto);
        return ResponseEntity.ok(resultDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteReport(@PathVariable("id") Long id){
        Boolean isDeleted = reportService.deleteReport(id);
        return ResponseEntity.ok(isDeleted);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ReportResponse>> searchReports(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ReportResponse> reportPage = reportService.getByKeyword(keyword, Pageable.ofSize(size));
        return ResponseEntity.ok(reportPage);
    }
}
