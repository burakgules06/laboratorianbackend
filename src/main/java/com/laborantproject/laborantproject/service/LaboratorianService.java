package com.laborantproject.laborantproject.service;

import com.laborantproject.laborantproject.model.dto.request.LaboratorianRequest;
import com.laborantproject.laborantproject.model.dto.response.LaboratorianResponse;

import java.util.List;

public interface LaboratorianService {
    LaboratorianRequest save(LaboratorianRequest labPersonalRequest);
    List<LaboratorianResponse> getPersonals();
    LaboratorianRequest findLabPersonalById(Long id);
    Boolean deletePersonal(Long id);
}
