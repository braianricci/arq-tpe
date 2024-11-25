package com.example.parada_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.parada_service.model.dto.*;

@Service
public interface ParadaService {
    
    List<ParadaResponse> getAllParadas();
    ParadaResponse getParadaById(String id);
    ResponseEntity<String> addParada(ParadaRequest parada);
    ResponseEntity<String> putParada(String id, ParadaRequest parada);
    ResponseEntity<String> deleteParada(String id);

}