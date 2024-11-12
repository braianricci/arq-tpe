package com.example.monopatin_service.service;

import java.util.List;

import com.example.monopatin_service.model.dto.*;

public interface MonopatinService {

    List<MonopatinResponse> getAllMonopatines();

    MonopatinResponse getMonopatinById(String id);

    void addMonopatin(MonopatinCreateRequest monopatin);

    void putMonopatin(String id, MonopatinUpdateRequest monopatin);

    void deleteMonopatin(String id);

    // Comunicacion entre 2 microservicios
    List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(Double latitud, Double longitud, Double radio);

    void marcarEnMantenimiento(String id);

    void marcarDisponible(String id);

    List<ReporteKilometrosDTO> obtenerUsoPorKilometros(boolean incluirTiempo);

    int obtenerCantidadMonopatinesEnOperacion();

    int obtenerCantidadMonopatinesEnMantenimiento();
}
