package com.example.monopatin_service.controller;

import java.util.List;
import com.example.monopatin_service.model.dto.reportes.*;
import com.example.monopatin_service.service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;


    //Generar reporte de uso de monopatines por kilómetros
    @GetMapping("/uso-por-kilometros")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReportePorKilometros() {
        List<ReporteKilometrosDTO> reporte = reporteService.obtenerUsoPorKilometros();
        return ResponseEntity.ok(reporte);
    }

    //Generar reporte de uso de monopatines por tiempo (con pausas)
    @GetMapping("/uso-por-tiempo-con-pausas")
    public ResponseEntity<List<ReporteTiempoDTO>> generarReportePorTiempoConPausas() {
        List<ReporteTiempoDTO> reporte = reporteService.obtenerUsoPorTiempoConPausas();
        return ResponseEntity.ok(reporte);
    }

    //Generar reporte de uso de monopatines por tiempo (sin pausas)
    @GetMapping("/uso-por-tiempo-sin-pausas")
    public ResponseEntity<List<ReporteTiempoDTO>> generarReportePorTiempoSinPausas() {
        List<ReporteTiempoDTO> reporte = reporteService.obtenerUsoPorTiempoSinPausas();
        return ResponseEntity.ok(reporte);
    }
}
