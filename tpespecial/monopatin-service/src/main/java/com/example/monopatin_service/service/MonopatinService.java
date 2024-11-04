package com.example.monopatin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.monopatin_service.model.dto.*;


@Service
public interface MonopatinService {
    
    List<MonopatinResponse> getAllMonopatines();
    MonopatinResponse getMonopatinById(String id);
    void addMonopatin(MonopatinCreateRequest usuario);
    void putMonopatin(String id, MonopatinUpdateRequest usuario);
    void deleteMonopatin(String id);

}
