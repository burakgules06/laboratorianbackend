package com.laborantproject.laborantproject.model.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ReportResponse {
    private Long fileNo;
    private String diagnosisTitle;
    private String diagnosis;
    private Date date;
    private String imageName;
    private Long labIdNo;
    private Long patientId;
}
