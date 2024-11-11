package com.example.viaje_service.controller;

import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.service.ViajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    // Obtener todos los viajes
    @GetMapping
    public ResponseEntity<List<ViajeResponse>> getAllViajes() {
        List<ViajeResponse> viajes = viajeService.getAllViajes();
        return ResponseEntity.ok(viajes);
    }

    // Obtener un viaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<ViajeResponse> getViajeById(@PathVariable Long id) {
        ViajeResponse viaje = viajeService.getViajeById(id);
        return ResponseEntity.ok(viaje);
    }

    // Crear un nuevo viaje
    @PostMapping("/add")
    public ResponseEntity<ViajeResponse> addViaje(@RequestBody ViajeCreateRequest viajeCreateRequest) {
        ViajeResponse nuevoViaje = viajeService.addViaje(viajeCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoViaje);
    }

    // Actualizar un viaje existente
    @PutMapping("put/{id}")
    public ResponseEntity<Void> updateViaje(@PathVariable Long id, @RequestBody ViajeUpdateRequest viajeUpdateRequest) {
        viajeService.updateViaje(id, viajeUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un viaje
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteViaje(@PathVariable Long id) {
        viajeService.deleteViaje(id);
        return ResponseEntity.noContent().build();
    }

    //REQUERIMIENTOS DEL ENUNCIADO
    // Endpoint para obtener monopatines con más de X viajes en un cierto año
    @GetMapping("/monopatines-con-mas-viajes")
    public ResponseEntity<List<String>> obtenerMonopatinesConMasViajes(
            @RequestParam int viajesMinimos,
            @RequestParam int anio) {
        List<String> monopatines = viajeService.obtenerMonopatinesConMasViajes(viajesMinimos, anio);
        return ResponseEntity.ok(monopatines);
    }
}
