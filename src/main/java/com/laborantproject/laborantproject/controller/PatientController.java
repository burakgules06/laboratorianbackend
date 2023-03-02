package com.laborantproject.laborantproject.controller;

import com.laborantproject.laborantproject.model.dto.request.PatientRequest;
import com.laborantproject.laborantproject.model.dto.response.PatientResponse;
import com.laborantproject.laborantproject.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/patient")
@CrossOrigin("*")

public class PatientController {
    private final PatientService patientService;

    @PostMapping("/new")
    private ResponseEntity<PatientRequest> savePatient(@RequestBody PatientRequest patientRequest){
        PatientRequest resultPatient = patientService.savePatient(patientRequest);
        return ResponseEntity.ok(resultPatient);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<PatientRequest> findPatient(@PathVariable("id") Long id){
        PatientRequest patient = patientService.findPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PatientResponse>> findAllPatients() {
        List<PatientResponse> patientRequests = patientService.getPatients();

        return ResponseEntity.ok(patientRequests);
    }
}

