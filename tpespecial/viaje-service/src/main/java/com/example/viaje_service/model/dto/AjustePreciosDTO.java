package com.example.viaje_service.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjustePreciosDTO {

    private BigDecimal nuevaTarifaNormal;
    private BigDecimal nuevaTarifaPausaExtendida;
    private LocalDate fechaEfectiva;
}
