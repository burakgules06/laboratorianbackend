package com.laborantproject.laborantproject.model.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ReportRequest {
    private Long fileNo;
    private String diagnosisTitle;
    private String diagnosis;
    private Date date;
    private String imageName;
    private Long labIdNo;
    private Long patientId;
}
