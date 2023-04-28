package com.laborantproject.laborantproject.model.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReportRequest {
    private Long fileNo;
    private String diagnosisTitle;
    private String diagnosis;
    private String attachmentId;
    private Date date;
    private String image;
    private Long labIdNo;
    private Long patientId;
}
