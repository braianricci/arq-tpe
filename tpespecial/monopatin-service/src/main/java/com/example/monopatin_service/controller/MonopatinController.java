package com.example.monopatin_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.monopatin_service.model.dto.*;
import com.example.monopatin_service.service.MonopatinService;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping
    public List<MonopatinResponse> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public MonopatinResponse getMonopatinById(@PathVariable String id) {
        return monopatinService.getMonopatinById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMonopatin(@RequestBody MonopatinCreateRequest monopatin) {
        monopatinService.addMonopatin(monopatin);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putMonopatin(@PathVariable String id, @RequestBody MonopatinUpdateRequest monopatin) {
        monopatinService.putMonopatin(id, monopatin);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMonopatin(@PathVariable String id) {
        monopatinService.deleteMonopatin(id);
    }

    // g.
    @GetMapping("/cercanos/{latitud}/{longitud}/{radio}")
    public List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(
            @PathVariable Double latitud,
            @PathVariable Double longitud,
            @PathVariable Double radio) {
        return monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio);
    }

    // REQUERIMIENTOS ENUNCIADO

    // Registrar monopatín en mantenimiento

    @PutMapping("/{id}/mantenimiento")
    public ResponseEntity<Void> registrarMantenimiento(@PathVariable String id) {
        monopatinService.marcarEnMantenimiento(id);
        return ResponseEntity.ok().build();
    }

    // Registrar fin de mantenimiento
    @PutMapping("/{id}/fin-mantenimiento")
    public ResponseEntity<Void> finalizarMantenimiento(@PathVariable String id) {
        monopatinService.marcarDisponible(id);
        return ResponseEntity.ok().build();
    }

    // Endpoing para consultar reporte de uso de monopatines por kilómetros para
    // establecer si un monopatín requiere de mantenimiento.
    @GetMapping("/uso-por-kilometros")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReportePorKilometros(
            @RequestBody IncluirTiempoEnPausaRequest request) {
        List<ReporteKilometrosDTO> reporte = monopatinService.obtenerUsoPorKilometros(request.isIncluirTiempo());
        return ResponseEntity.ok(reporte);
    }

    // Endpoing para consultar la cantidad de monopatines en operacion vs cantidad
    // en mantenimiento
    @GetMapping("/total-en-operacion-y-mantenimiento")
    public ResponseEntity<MonopatinesOperacionMantenimientoDTO> obtenerMonopatinesEnOperacionYMantenimiento() {
        int enOperacion = monopatinService.obtenerCantidadMonopatinesEnOperacion();
        int enMantenimiento = monopatinService.obtenerCantidadMonopatinesEnMantenimiento();

        MonopatinesOperacionMantenimientoDTO response = new MonopatinesOperacionMantenimientoDTO(
                enOperacion,
                enMantenimiento);
        return ResponseEntity.ok(response);
    }
}
