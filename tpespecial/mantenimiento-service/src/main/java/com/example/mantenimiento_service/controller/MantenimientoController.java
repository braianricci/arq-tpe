package com.example.mantenimiento_service.controller;

import com.example.mantenimiento_service.model.dto.*;
import com.example.mantenimiento_service.service.MantenimientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mantenimiento Controller", description = "APIs para gestionar mantenimientos")
@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    @Operation(summary = "Obtener todos los registros de mantenimientos")
    @GetMapping
    public List<MantenimientoResponse> getAllMantenimientos() {
        return mantenimientoService.getAllMantenimientos();
    }

    @Operation(summary = "Obtener un registro de mantenimiento por ID")
    @GetMapping("/{id}")
    public MantenimientoResponse getMantenimientoById(@PathVariable Long id) {
        return mantenimientoService.getMantenimientoById(id);
    }

    @Operation(summary = "Crear un nuevo registro de mantenimiento")
    @PostMapping("/add")
    public ResponseEntity<String> addMantenimiento(@RequestBody MantenimientoCreateRequest mantenimientoCreateRequest) {
        return mantenimientoService.addMantenimiento(mantenimientoCreateRequest);
    }

    @Operation(summary = "Actualizar un registro de mantenimiento")
    @PutMapping("put/{id}")
    public ResponseEntity<String> updateMantenimiento(@PathVariable Long id, @RequestBody MantenimientoUpdateRequest mantenimientoUpdateRequest) {
        return mantenimientoService.updateMantenimiento(id, mantenimientoUpdateRequest);
    }

    @Operation(summary = "Eliminar un resgistro de mantenimiento")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMantenimiento(@PathVariable Long id) {
        return mantenimientoService.deleteMantenimiento(id);
    }
}
