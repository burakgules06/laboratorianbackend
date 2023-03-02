package com.laborantproject.laborantproject.service;

import com.laborantproject.laborantproject.model.dto.request.PatientRequest;
import com.laborantproject.laborantproject.model.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {
    PatientRequest savePatient(PatientRequest patientRequest);
    PatientRequest findPatientById(Long id);
    List<PatientResponse> getPatients();

}
