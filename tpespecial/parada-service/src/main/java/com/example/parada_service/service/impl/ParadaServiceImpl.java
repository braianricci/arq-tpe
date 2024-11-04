package com.example.parada_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void addParada(ParadaRequest paradaRequest) {
        Parada nuevaParada = new Parada(paradaRequest.getNombre(), paradaRequest.getUbicacion());
        paradaRepository.save(nuevaParada);
    }

    public void putParada(String id, ParadaRequest paradaRequest) {
        Optional<Parada> paradaOpt = paradaRepository.findById(id);
        if (paradaOpt.isPresent()) {
            Parada parada = paradaOpt.get();
            parada.setNombre(paradaRequest.getNombre());
            parada.setUbicacion(paradaRequest.getUbicacion());
            paradaRepository.save(parada);
        }
    }

    public void deleteParada(String id) {
        paradaRepository.deleteById(id);
    }

    

    // Método para convertir Parada a ParadaResponse
    private ParadaResponse convertToResponse(Parada parada) {
        return new ParadaResponse(parada.getId(), parada.getNombre(), parada.getUbicacion(), parada.getMonopatines());
    }

}
