package com.example.parada_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.parada_service.model.dto.*;

@Service
public interface ParadaService {
    
    List<ParadaResponse> getAllParadas();
    ParadaResponse getParadaById(String id);
    void addParada(ParadaRequest parada);
    void putParada(String id, ParadaRequest parada);
    void deleteParada(String id);

}