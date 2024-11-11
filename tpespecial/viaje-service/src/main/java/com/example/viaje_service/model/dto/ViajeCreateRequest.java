package com.example.viaje_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeCreateRequest {
    private Long usuarioId;  
    private String monopatinId;    
    private LocalDateTime fechaInicio;
    private BigDecimal tarifaAplicada;
}
