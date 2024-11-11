package com.example.viaje_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinesConMasViajesRequest {
    private int viajesMinimos;
    private int anio;
}