package com.example.monopatin_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncluirTiempoEnPausaRequest {
    private boolean incluirTiempo;
}