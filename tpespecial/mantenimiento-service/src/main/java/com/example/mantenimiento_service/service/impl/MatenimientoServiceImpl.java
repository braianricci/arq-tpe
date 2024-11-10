package com.example.mantenimiento_service.service.impl;

import com.example.mantenimiento_service.model.dto.*;
import com.example.mantenimiento_service.model.entity.Mantenimiento;
import com.example.mantenimiento_service.repository.MantenimientoRepository;
import com.example.mantenimiento_service.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    // CRUD basico

    @Override
    public List<MantenimientoResponse> getAllMantenimientos() {
        List<Mantenimiento> viajes = mantenimientoRepository.findAll();
        return viajes.stream().map(this::convertToDto).toList();
    }

    @Override
    public MantenimientoResponse getMantenimientoById(Long id) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));
        return convertToDto(mantenimiento);
    }

    @Override
    public MantenimientoResponse addMantenimiento(MantenimientoCreateRequest mantenimientoRequest) {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setIdMonopatin(mantenimientoRequest.getMonopatinId());
        mantenimiento.setFechaInicio(LocalDateTime.now());
        mantenimiento.setEstado(mantenimientoRequest.getEstado());
        mantenimientoRepository.save(mantenimiento);
        return convertToDto(mantenimiento);
    }

    @Override
    public void updateMantenimiento(Long id, MantenimientoUpdateRequest mantenimientoRequest) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));
        mantenimiento.setFechaFin(mantenimientoRequest.getFechaFin());
        mantenimiento.setEstado(mantenimientoRequest.getEstado());
        mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public void deleteMantenimiento(Long id) {
        mantenimientoRepository.deleteById(id);
    }

    // Método auxiliar para convertir entidad a DTO
    private MantenimientoResponse convertToDto(Mantenimiento mantenimiento) {
        return new MantenimientoResponse(
                mantenimiento.getId(),
                mantenimiento.getIdMonopatin(),
                mantenimiento.getFechaInicio(),
                mantenimiento.getFechaFin(),
                mantenimiento.getEstado());
    }
}
