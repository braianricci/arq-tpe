package com.example.monopatin_service.service;

import java.util.List;

import com.example.monopatin_service.model.dto.reportes.ReporteKilometrosDTO;
import com.example.monopatin_service.model.dto.reportes.ReporteTiempoDTO;


public interface ReporteService {

    List<ReporteKilometrosDTO> obtenerUsoPorKilometros();

    List<ReporteTiempoDTO> obtenerUsoPorTiempoConPausas();

    List<ReporteTiempoDTO> obtenerUsoPorTiempoSinPausas();
 
}
