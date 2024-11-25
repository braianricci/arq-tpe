package com.example.mantenimiento_service.service.impl;

import com.example.mantenimiento_service.model.dto.*;
import com.example.mantenimiento_service.model.entity.Mantenimiento;
import com.example.mantenimiento_service.repository.MantenimientoRepository;
import com.example.mantenimiento_service.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

 
    @Override
    public List<MantenimientoResponse> getAllMantenimientos() {
        List<Mantenimiento> mantenimiento = mantenimientoRepository.findAll();
        return mantenimiento.stream().map(this::convertToDto).toList();
    }

    @Override
    public MantenimientoResponse getMantenimientoById(Long id) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));
        return convertToDto(mantenimiento);
    }

    @Override
    public ResponseEntity<String> addMantenimiento(MantenimientoCreateRequest mantenimientoRequest) {
        Mantenimiento mantenimiento = new Mantenimiento(mantenimientoRequest.getMonopatinId());
        mantenimientoRepository.save(mantenimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de mantenimiento creado exitosamente.");
    }

    @Override
    public ResponseEntity<String> updateMantenimiento(Long id, MantenimientoUpdateRequest mantenimientoRequest) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id).orElse(null);
        if(mantenimiento != null){
            mantenimiento.setFechaFin(mantenimientoRequest.getFechaFin());
            mantenimiento.setEstado(mantenimientoRequest.getEstado());
            mantenimientoRepository.save(mantenimiento);
            return ResponseEntity.status(HttpStatus.OK).body("Mantenimiento actualizado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe registro de mantenimiento con ese ID.");
    }

    @Override
    public ResponseEntity<String> deleteMantenimiento(Long id) {
        Mantenimiento mantenimiento = mantenimientoRepository.findById(id).orElse(null);
        if(mantenimiento != null){
            mantenimientoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Mantenimiento eliminado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe registro de mantenimiento con ese ID.");
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
