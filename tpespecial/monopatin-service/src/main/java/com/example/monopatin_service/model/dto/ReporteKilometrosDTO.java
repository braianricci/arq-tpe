package com.example.monopatin_service.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteKilometrosDTO {
    private String monopatinId;
    private String modelo;
    private BigDecimal kilometrajeTotal;
    private Integer tiempoEnPausa;

    public ReporteKilometrosDTO(String monopatinId, String modelo, BigDecimal kilometrajeTotal) {
        this.monopatinId = monopatinId;
        this.modelo = modelo;
        this.kilometrajeTotal = kilometrajeTotal;
    }
}
