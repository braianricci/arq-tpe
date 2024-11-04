package com.example.monopatin_service.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.monopatin_service.model.dto.*;
import com.example.monopatin_service.model.entity.Monopatin;
import com.example.monopatin_service.repository.MonopatinRepository;
import com.example.monopatin_service.service.MonopatinService;

@Service
public class MonopatinServiceImpl implements MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Override
    public List<MonopatinResponse> getAllMonopatines() {
        List<Monopatin> monopatines = monopatinRepository.findAll();
        return monopatines.stream()
                .map(m -> new MonopatinResponse(m.getId() ,m.getModelo(), m.getEstado(), m.getUbicacion(), m.getKilometrajeTotal(),
                        m.getTiempoUsoTotal(), m.getUltimoMantenimiento()))
                .collect(Collectors.toList());
    }

    @Override
    public MonopatinResponse getMonopatinById(String id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if (monopatin != null) {
            return new MonopatinResponse(
                    monopatin.getId(),
                    monopatin.getModelo(),
                    monopatin.getEstado(),
                    monopatin.getUbicacion(),
                    monopatin.getKilometrajeTotal(),
                    monopatin.getTiempoUsoTotal(),
                    monopatin.getUltimoMantenimiento());
        }
        return null;
    }

    @Override
    public void addMonopatin(MonopatinCreateRequest monopatinRequest) {
        // Verificar si el monopatin ya existe (por modelo o algún otro criterio único,
        // si aplica)
        Optional<Monopatin> existingMonopatin = monopatinRepository.findByModelo(monopatinRequest.getModelo());
        if (existingMonopatin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El monopatin ya existe.");
        }

        Monopatin monopatin = new Monopatin(monopatinRequest.getModelo());
        monopatinRepository.save(monopatin);
    }

    @Override
    public void putMonopatin(String id, MonopatinUpdateRequest monopatinRequest) {
        Monopatin monopatin = monopatinRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Monopatin con id " + id + " no encontrado"));

        monopatin.setModelo(monopatinRequest.getModelo());
        monopatin.setEstado(monopatinRequest.getEstado());
        monopatin.setUbicacion(monopatinRequest.getUbicacion());
        monopatin.setKilometrajeTotal(monopatinRequest.getKilometrajeTotal());
        monopatin.setTiempoUsoTotal(monopatinRequest.getTiempoUsoTotal());
        monopatin.setUltimoMantenimiento(monopatinRequest.getUltimoMantenimiento());
        monopatinRepository.save(monopatin);
    }

    @Override
    public void deleteMonopatin(String id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if (monopatin != null) {
            monopatinRepository.delete(monopatin);
        }
    }

}
