package com.example.mantenimiento_service.service;

import java.util.List;
import com.example.mantenimiento_service.model.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface MantenimientoService {

    // Metodos CRUD
    List<MantenimientoResponse> getAllMantenimientos();

    MantenimientoResponse getMantenimientoById(Long id);

    MantenimientoResponse addMantenimiento(MantenimientoCreateRequest mantenimiento);

    void updateMantenimiento(Long id, MantenimientoUpdateRequest mantenimiento);

    void deleteMantenimiento(Long id);
}
