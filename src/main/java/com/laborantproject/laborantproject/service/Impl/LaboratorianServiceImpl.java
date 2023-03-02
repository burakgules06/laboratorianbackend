package com.laborantproject.laborantproject.service.Impl;

import com.laborantproject.laborantproject.model.dto.request.LaboratorianRequest;
import com.laborantproject.laborantproject.model.dto.response.LaboratorianResponse;
import com.laborantproject.laborantproject.model.entity.Laboratorian;
import com.laborantproject.laborantproject.repository.LaboratorianRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.laborantproject.laborantproject.service.LaboratorianService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class LaboratorianServiceImpl implements LaboratorianService {

    private final LaboratorianRepository laboratorianRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public LaboratorianRequest save(LaboratorianRequest laboratorianRequest) {
        Laboratorian laboratorian = modelMapper.map(laboratorianRequest, Laboratorian.class);
        return modelMapper.map(laboratorianRepository.save(laboratorian), LaboratorianRequest.class);
    }

    @Override
    public List<LaboratorianResponse> getPersonals() {
        List<Laboratorian> laboratorians = laboratorianRepository.findAll();
        List<LaboratorianResponse> dtos = laboratorians.stream()
                .map(laboratorian -> modelMapper.map(laboratorian, LaboratorianResponse.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public LaboratorianRequest findLabPersonalById(Long id) {
        Laboratorian labPersonal = laboratorianRepository.findById(id).orElseThrow(() -> new RuntimeException("id yanlış"));

       return modelMapper.map(labPersonal, LaboratorianRequest.class);

    }

    @Override
    public Boolean deletePersonal(Long id) {
        Optional<Laboratorian> labPersonal = laboratorianRepository.findById(id);
        if(labPersonal.isPresent()){
            laboratorianRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
