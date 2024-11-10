package com.example.monopatin_service.model.dto.reportes;

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
}
