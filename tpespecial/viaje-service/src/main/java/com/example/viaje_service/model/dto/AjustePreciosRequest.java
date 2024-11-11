package com.example.viaje_service.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjustePreciosRequest {

    private Double nuevaTarifaNormal;
    private Double nuevaTarifaPausaExtendida;
    private LocalDate fechaEfectiva;
}
