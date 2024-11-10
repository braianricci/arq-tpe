package com.example.mantenimiento_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.mantenimiento_service.model.entity.Mantenimiento.EstadoMantenimiento;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoResponse {
    private Long id;
    private Long monopatinId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoMantenimiento estado;
}