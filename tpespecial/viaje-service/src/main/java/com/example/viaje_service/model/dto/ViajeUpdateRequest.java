package com.example.viaje_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.viaje_service.model.entity.Viaje.EstadoViaje;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeUpdateRequest {

    private LocalDateTime fechaFin;
    private BigDecimal kilometrosRecorridos;
    private EstadoViaje estado; 
    private BigDecimal tarifaAplicada;

}
