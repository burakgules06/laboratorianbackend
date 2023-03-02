package com.laborantproject.laborantproject.model.dto.response;

import lombok.Data;

@Data
public class PatientResponse {
    private Long patientId;
    private String name;
    private String surname;
    private String tcNo;
}
