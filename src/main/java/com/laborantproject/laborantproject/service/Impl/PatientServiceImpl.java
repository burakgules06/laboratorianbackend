package com.laborantproject.laborantproject.service.Impl;

import com.laborantproject.laborantproject.model.dto.request.PatientRequest;
import com.laborantproject.laborantproject.model.dto.response.PatientResponse;
import com.laborantproject.laborantproject.model.entity.Patient;
import com.laborantproject.laborantproject.repository.PatientRepository;
import com.laborantproject.laborantproject.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;

    @Override
    public PatientRequest savePatient(PatientRequest patientRequest) {
        Patient patient = modelMapper.map(patientRequest, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientRequest.class);
    }

    @Override
    public PatientRequest findPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("id yanlış"));
        return modelMapper.map(patient, PatientRequest.class);
    }

    @Override
    public List<PatientResponse> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponse> dtos = patients.stream()
                .map(patient -> modelMapper.map(patient, PatientResponse.class))
                .collect(Collectors.toList());
        return dtos;
    }

}
