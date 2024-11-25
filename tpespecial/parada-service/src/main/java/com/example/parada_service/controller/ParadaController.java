package com.example.parada_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.parada_service.model.dto.*;
import com.example.parada_service.service.ParadaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Parada Controller", description = "APIs para gestionar paradas")
@RestController
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;


    @Operation(summary = "Obtener todas las paradas")
    @GetMapping
    public List<ParadaResponse> getAllParadas() {
        return paradaService.getAllParadas();
    }

    @Operation(summary = "Obtener una parada por ID")
    @GetMapping("/{id}")
    public ParadaResponse getParadaById(@PathVariable String id) {
        return paradaService.getParadaById(id);
    }

    @Operation(summary = "Crear una parada")
    @PostMapping("/add")
    public ResponseEntity<String> addParada(@RequestBody ParadaRequest parada) {
        return paradaService.addParada(parada);
    }

    @Operation(summary = "Actualizar una parada")
    @PutMapping("/put/{id}")
    public ResponseEntity<String> updateParada(@PathVariable String id, @RequestBody ParadaRequest parada) {
        return paradaService.putParada(id, parada);
    }

    @Operation(summary = "Eliminar una parada")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteParada(@PathVariable String id) {
        return paradaService.deleteParada(id);
    }
}