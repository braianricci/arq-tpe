package com.example.mantenimiento_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.mantenimiento_service.model.entity.Mantenimiento.EstadoMantenimiento;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MantenimientoUpdateRequest {

    private LocalDateTime fechaFin;
    private EstadoMantenimiento estado;
}
