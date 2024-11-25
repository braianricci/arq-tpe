package com.example.parada_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.parada_service.model.dto.*;
import com.example.parada_service.model.entity.Parada;
import com.example.parada_service.repository.ParadaRepository;
import com.example.parada_service.service.ParadaService;

@Service
public class ParadaServiceImpl implements ParadaService{

 @Autowired
    private ParadaRepository paradaRepository;

    public List<ParadaResponse> getAllParadas() {
        return paradaRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    public ParadaResponse getParadaById(String id) {
        Optional<Parada> paradaOpt = paradaRepository.findById(id);
        return paradaOpt.map(this::convertToResponse).orElse(null);
    }

    public ResponseEntity<String> addParada(ParadaRequest paradaRequest) {
        Parada nuevaParada = new Parada(paradaRequest.getNombre(), paradaRequest.getUbicacion());
        paradaRepository.save(nuevaParada);
        return ResponseEntity.status(HttpStatus.OK).body("Parada creada exitosamente.");
    }

    public ResponseEntity<String> putParada(String id, ParadaRequest paradaRequest) {
        Parada parada = paradaRepository.findById(id).orElse(null);
        if (parada != null) {
            parada.setNombre(paradaRequest.getNombre());
            parada.setUbicacion(paradaRequest.getUbicacion());
            paradaRepository.save(parada);
            return ResponseEntity.status(HttpStatus.OK).body("Parada actualizada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La parada no existe");
    }
        

    public ResponseEntity<String> deleteParada(String id) {
        Parada parada = paradaRepository.findById(id).orElse(null);
        if (parada != null) {
            paradaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Parada eliminada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La parada no existe");
    }

    

    // Método para convertir Parada a ParadaResponse
    private ParadaResponse convertToResponse(Parada parada) {
        return new ParadaResponse(parada.getId(), parada.getNombre(), parada.getUbicacion(), parada.getMonopatines());
    }

}
