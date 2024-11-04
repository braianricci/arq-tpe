package com.example.monopatin_service.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.geo.Point;

import com.example.monopatin_service.model.entity.Monopatin.EstadoMonopatin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinResponse {

    private String id;
    private String modelo;
    private EstadoMonopatin estado;
    private Point ubicacion;
    private BigDecimal kilometrajeTotal;
    private Integer tiempoUsoTotal;
    private LocalDateTime ultimoMantenimiento;
}