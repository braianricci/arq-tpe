package com.example.viaje_service.controller;

import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.service.ViajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/viajes")
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

    // REQUERIMIENTOS DEL ENUNCIADO

    // Obtener monopatines con más de X viajes en un cierto año
    @PostMapping("/monopatines-con-mas-viajes")
    public ResponseEntity<List<String>> obtenerMonopatinesConMasViajes(
            @RequestBody MonopatinesConMasViajesRequest request) {
        List<String> monopatines = viajeService.obtenerMonopatinesConMasViajes(
                request.getViajesMinimos(), request.getAnio());
        return ResponseEntity.ok(monopatines);
    }

    // Obtener total facturado en rango de meses de un año
    @PostMapping("/total-facturado")
    public ResponseEntity<BigDecimal> obtenerTotalFacturadoPorRangoDeMesesEnAnio(
            @RequestBody TotalFacturadoRequest request) {
        BigDecimal total = viajeService.obtenerTotalFacturadoPorRangoDeMesesEnAnio(
                request.getAnio(), request.getDesdeMes(), request.getHastaMes());
        return ResponseEntity.ok(total);
    }

    // Modificar precios con una fecha a partir de la cual son
    // efectivos. A partir de ahora, antes de agregar un viaje es necesario que exista un
    // precio con fecha efectiva anterior a la del viaje.
    @PostMapping("/ajustar-precios")
    public ResponseEntity<PrecioResponse> ajustarPrecios(@RequestBody AjustePreciosRequest request) {
        PrecioResponse precioResponse = viajeService.ajustarPrecios(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(precioResponse);
    }
}
