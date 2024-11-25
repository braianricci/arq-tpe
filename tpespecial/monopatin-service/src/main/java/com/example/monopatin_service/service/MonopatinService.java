package com.example.monopatin_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.monopatin_service.model.dto.*;

public interface MonopatinService {

    List<MonopatinResponse> getAllMonopatines();

    MonopatinResponse getMonopatinById(String id);

    ResponseEntity<String> addMonopatin(MonopatinCreateRequest monopatin);

    ResponseEntity<String> putMonopatin(String id, MonopatinUpdateRequest monopatin);

    ResponseEntity<String> deleteMonopatin(String id);

    // Comunicacion entre 2 microservicios
    List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(Double latitud, Double longitud, Double radio);

    ResponseEntity<String> marcarEnMantenimiento(String id);

    ResponseEntity<String> marcarDisponible(String id);

    List<ReporteKilometrosDTO> obtenerUsoPorKilometros(boolean incluirTiempo);

    int obtenerCantidadMonopatinesEnOperacion();

    int obtenerCantidadMonopatinesEnMantenimiento();
}
