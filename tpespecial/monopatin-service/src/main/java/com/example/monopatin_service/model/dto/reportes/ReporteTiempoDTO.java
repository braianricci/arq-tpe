package com.example.monopatin_service.model.dto.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTiempoDTO {

    private String monopatinId;
    private String modelo;
    private Integer tiempoUsoTotal; // en minutos
    private Integer tiempoEnPausa;  // en minutos

}
