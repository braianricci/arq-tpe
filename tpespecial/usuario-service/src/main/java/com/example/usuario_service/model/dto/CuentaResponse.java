package com.example.usuario_service.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse {

    private Long id;
    private String mercadoPagoId;
    private int creditos;
    private LocalDate fechaDeCreacion;
    private boolean habilitada;
    
}
