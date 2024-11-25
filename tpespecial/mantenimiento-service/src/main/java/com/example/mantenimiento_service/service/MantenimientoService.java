package com.example.mantenimiento_service.service;

import java.util.List;
import com.example.mantenimiento_service.model.dto.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MantenimientoService {

    // Metodos CRUD
    List<MantenimientoResponse> getAllMantenimientos();

    MantenimientoResponse getMantenimientoById(Long id);

    ResponseEntity<String> addMantenimiento(MantenimientoCreateRequest mantenimiento);

    ResponseEntity<String> updateMantenimiento(Long id, MantenimientoUpdateRequest mantenimiento);

    ResponseEntity<String> deleteMantenimiento(Long id);
}
