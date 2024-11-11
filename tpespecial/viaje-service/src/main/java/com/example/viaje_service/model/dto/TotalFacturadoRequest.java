package com.example.viaje_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalFacturadoRequest {
    private int anio;
    private int desdeMes;
    private int hastaMes;
}
