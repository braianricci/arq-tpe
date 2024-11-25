package com.example.monopatin_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.monopatin_service.model.dto.*;
import com.example.monopatin_service.service.MonopatinService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Monopatin Controller", description = "APIs para gestionar monopatines")
@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @Operation(summary = "Obtener todos los monopatines")
    @GetMapping
    public List<MonopatinResponse> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @Operation(summary = "Obtener monopatin por ID")
    @GetMapping("/{id}")
    public MonopatinResponse getMonopatinById(@PathVariable String id) {
        return monopatinService.getMonopatinById(id);
    }

    @Operation(summary = "Crear un monopatin")
    @PostMapping("/add")
    public ResponseEntity<String> addMonopatin(@RequestBody MonopatinCreateRequest monopatin) {
        return monopatinService.addMonopatin(monopatin);
    }

    @Operation(summary = "Actualizar un monopatin")
    @PutMapping("/put/{id}")
    public ResponseEntity<String> putMonopatin(@PathVariable String id, @RequestBody MonopatinUpdateRequest monopatin) {
        return monopatinService.putMonopatin(id, monopatin);
    }

    @Operation(summary = "Eliminar un monopatin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMonopatin(@PathVariable String id) {
        return monopatinService.deleteMonopatin(id);
    }

    // REQUERIMIENTOS ENUNCIADO

    // Registrar monopatín en mantenimiento

    @Operation(summary = "Resgistrar un monopatín en mantenimiento")
    @PutMapping("/{id}/mantenimiento")
    public ResponseEntity<Void> registrarMantenimiento(@PathVariable String id) {
        monopatinService.marcarEnMantenimiento(id);
        return ResponseEntity.ok().build();
    }

    // Registrar fin de mantenimiento
    @Operation(summary = "Registrar fin de mantenimiento de un monopatin")
    @PutMapping("/{id}/fin-mantenimiento")
    public ResponseEntity<Void> finalizarMantenimiento(@PathVariable String id) {
        monopatinService.marcarDisponible(id);
        return ResponseEntity.ok().build();
    }

    // Reporte uso de monopatines por kilómetros para
    // establecer si un monopatín requiere de mantenimiento.
    @Operation(summary = "Reporte de uso de los monopatines en km")
    @GetMapping("/uso-por-kilometros")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReportePorKilometros(@RequestBody IncluirTiempoEnPausaRequest request) {
        List<ReporteKilometrosDTO> reporte = monopatinService.obtenerUsoPorKilometros(request.isIncluirTiempo());
        return ResponseEntity.ok(reporte);
    }

    // Consultar la cantidad de monopatines en operacion vs cantidad
    // en mantenimiento
    @Operation(summary = "Cantidad de monopatines en operacion y cantidad en mantenimiento")
    @GetMapping("/total-en-operacion-y-mantenimiento")
    public ResponseEntity<MonopatinesOperacionMantenimientoDTO> obtenerMonopatinesEnOperacionYMantenimiento() {
        int enOperacion = monopatinService.obtenerCantidadMonopatinesEnOperacion();
        int enMantenimiento = monopatinService.obtenerCantidadMonopatinesEnMantenimiento();

        MonopatinesOperacionMantenimientoDTO response = new MonopatinesOperacionMantenimientoDTO(
                enOperacion,
                enMantenimiento);
        return ResponseEntity.ok(response);
    }

    // 3.g.Listado de los monopatines cercanos a mi zona, para poder encontrar
    //     un monopatín cerca de mi ubicación
    @Operation(summary = "Monopatines cercanos a mi zona")
    @GetMapping("/cercanos/{latitud}/{longitud}/{radio}")
    public List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(
            @PathVariable Double latitud,
            @PathVariable Double longitud,
            @PathVariable Double radio) {
        return monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio);
    }
}
