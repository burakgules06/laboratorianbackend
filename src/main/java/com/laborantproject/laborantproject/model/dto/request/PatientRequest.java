package com.laborantproject.laborantproject.model.dto.request;

import lombok.Data;

@Data
public class PatientRequest {
    private Long patientId;
    private String name;
    private String surname;
    private String tcNo;

}
