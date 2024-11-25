package com.example.monopatin_service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.monopatin_service.model.dto.*;
import com.example.monopatin_service.model.entity.Monopatin;
import com.example.monopatin_service.model.entity.Monopatin.EstadoMonopatin;
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
                .map(m -> new MonopatinResponse(m.getId(), m.getModelo(), m.getEstado(), m.getUbicacion(),
                        m.getKilometrajeTotal(),
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
    public ResponseEntity<String> addMonopatin(MonopatinCreateRequest monopatinRequest) {
        
        Optional<Monopatin> existingMonopatin = monopatinRepository.findByModelo(monopatinRequest.getModelo());
        if (existingMonopatin.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya existe.");
        }
        Monopatin monopatin = new Monopatin(monopatinRequest.getModelo());
        monopatinRepository.save(monopatin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Monopatin creado exitosamente.");
    }

    @Override
    public ResponseEntity<String> putMonopatin(String id, MonopatinUpdateRequest monopatinRequest) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if(monopatin != null){
            monopatin.setUbicacion(monopatinRequest.getUbicacion());
            monopatinRepository.save(monopatin);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin actualizado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya existe.");
    }

    @Override
    public ResponseEntity<String> deleteMonopatin(String id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if (monopatin != null) {
            monopatinRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin eliminado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya existe.");
    }

    @Override
    public List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(Double longitud, Double altitud, Double radio) {
        // Pasar de una ubicacion (x,y) a Point
        Point ubicacion = new Point(longitud, altitud);
        // Crear la distancia con el radio en kilómetros
        Distance distancia = new Distance(radio, Metrics.KILOMETERS); // El radio debe estar en la unidad
                                                                      // correspondiente (por ejemplo, kilómetros)

        List<Monopatin> monopatines = monopatinRepository.findByUbicacionNear(ubicacion, distancia);

        return monopatines.stream().map(this::convertiCercanosDTO).collect(Collectors.toList());
    }

    // Método para convertir de entidad a DTO
    private MonopatinesCercanosDTO convertiCercanosDTO(Monopatin monopatin) {
        return new MonopatinesCercanosDTO(monopatin.getId(), monopatin.getUbicacion());
    }

    @Override
    public ResponseEntity<String> marcarEnMantenimiento(String id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if (monopatin != null) {
            monopatin.setEstado(EstadoMonopatin.EN_MANTENIMIENTO);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin puesto en mantenimiento.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin que intenta poner en mantenimiento no existe.");
    }

    @Override
    public ResponseEntity<String> marcarDisponible(String id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if (monopatin != null) {
            monopatin.setEstado(EstadoMonopatin.DISPONIBLE);
            return ResponseEntity.status(HttpStatus.OK).body("El monopatin vuelve a estar disponible.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin que intenta poner como disponible no existe.");
    }

    @Override
    public List<ReporteKilometrosDTO> obtenerUsoPorKilometros(boolean incluirTiempo) {
        if (incluirTiempo) {
            return monopatinRepository.findAll().stream()
                    .map(m -> new ReporteKilometrosDTO(m.getId(), m.getModelo(), m.getKilometrajeTotal(),
                            m.getTiempoEnPausa()))
                    .collect(Collectors.toList());
        } else {
            return monopatinRepository.findAll().stream()
                    .map(m -> new ReporteKilometrosDTO(m.getId(), m.getModelo(), m.getKilometrajeTotal()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public int obtenerCantidadMonopatinesEnOperacion() {
        return monopatinRepository.countByEstado(EstadoMonopatin.DISPONIBLE) +
                monopatinRepository.countByEstado(EstadoMonopatin.EN_USO);
    }

    @Override
    public int obtenerCantidadMonopatinesEnMantenimiento() {
        return monopatinRepository.countByEstado(EstadoMonopatin.EN_MANTENIMIENTO);
    }
}
