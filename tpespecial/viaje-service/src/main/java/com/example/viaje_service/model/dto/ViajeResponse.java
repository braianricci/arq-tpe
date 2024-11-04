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
public class ViajeResponse {
    private Long id;                       
    private Long usuarioId;                
    private Long monopatinId;                
    private LocalDateTime fechaInicio;       
    private LocalDateTime fechaFin;         
    private BigDecimal kilometrosRecorridos; 
    private Long paradaInicioId;
    private Long paradaFinId;
    private EstadoViaje estado;                   
    private BigDecimal tarifaAplicada;       
}