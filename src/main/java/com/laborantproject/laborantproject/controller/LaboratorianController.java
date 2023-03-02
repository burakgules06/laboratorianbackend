package com.laborantproject.laborantproject.controller;


import com.laborantproject.laborantproject.model.dto.request.LaboratorianRequest;
import com.laborantproject.laborantproject.model.dto.response.LaboratorianResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laborantproject.laborantproject.service.LaboratorianService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/labpersonal")
@CrossOrigin("http://localhost:3000")
@RestController
public class LaboratorianController {
    private final LaboratorianService laboratorianService;

    @PostMapping("/new")
    public ResponseEntity<LaboratorianRequest> createLabPersonal(@RequestBody LaboratorianRequest laboratorianRequest){
        LaboratorianRequest resultPersonal = laboratorianService.save(laboratorianRequest);
        return ResponseEntity.ok(resultPersonal);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<LaboratorianResponse>> findAllPersonals(){
        List<LaboratorianResponse> personalDTOS = laboratorianService.getPersonals();
        return ResponseEntity.ok(personalDTOS);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<LaboratorianRequest> findPersonal(@PathVariable("id") Long id){
        LaboratorianRequest personal = laboratorianService.findLabPersonalById(id);
        return ResponseEntity.ok(personal);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<LaboratorianRequest> updateLabPersonal(@PathVariable("id") Long id,
                                                                 @RequestBody LaboratorianRequest laboratorianRequest){
        LaboratorianRequest personalDTO = laboratorianService.findLabPersonalById(id);
        personalDTO.setName(laboratorianRequest.getName());
        personalDTO.setSurname(laboratorianRequest.getSurname());
        return ResponseEntity.ok(laboratorianService.save(personalDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteLabPersonal(@PathVariable("id") Long id){
        Boolean isDeleted = laboratorianService.deletePersonal(id);
        return ResponseEntity.ok(isDeleted);
    }
}

