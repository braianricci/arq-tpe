package com.example.monopatin_service.service;

import java.util.List;

import com.example.monopatin_service.model.dto.*;


public interface MonopatinService {
    
    List<MonopatinResponse> getAllMonopatines();
    MonopatinResponse getMonopatinById(String id);
    void addMonopatin(MonopatinCreateRequest monopatin);
    void putMonopatin(String id, MonopatinUpdateRequest monopatin);
    void deleteMonopatin(String id);
    List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(Double latitud, Double longitud, Double radio); //COMUNICACION ENTRE 2 MICROSERVICIOS
    void marcarEnMantenimiento(String id);
    void marcarDisponible(String id);
}
