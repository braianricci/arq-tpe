package com.example.mantenimiento_service.controller;

import com.example.mantenimiento_service.model.dto.*;
import com.example.mantenimiento_service.service.MantenimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    // Obtener todos los mantenimientos
    @GetMapping
    public ResponseEntity<List<MantenimientoResponse>> getAllMantenimientos() {
        List<MantenimientoResponse> viajes = mantenimientoService.getAllMantenimientos();
        return ResponseEntity.ok(viajes);
    }

    // Obtener un mantenimiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoResponse> getMantenimientoById(@PathVariable Long id) {
        MantenimientoResponse mantenimiento = mantenimientoService.getMantenimientoById(id);
        return ResponseEntity.ok(mantenimiento);
    }

    // Crear un nuevo mantenimiento
    @PostMapping("/add")
    public ResponseEntity<MantenimientoResponse> addMantenimiento(
            @RequestBody MantenimientoCreateRequest mantenimientoCreateRequest) {
        MantenimientoResponse nuevoMantenimiento = mantenimientoService.addMantenimiento(mantenimientoCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMantenimiento);
    }

    // Actualizar un mantenimiento existente
    @PutMapping("put/{id}")
    public ResponseEntity<Void> updateMantenimiento(@PathVariable Long id,
            @RequestBody MantenimientoUpdateRequest mantenimientoUpdateRequest) {
        mantenimientoService.updateMantenimiento(id, mantenimientoUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un mantenimiento
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteMantenimiento(@PathVariable Long id) {
        mantenimientoService.deleteMantenimiento(id);
        return ResponseEntity.noContent().build();
    }
}
