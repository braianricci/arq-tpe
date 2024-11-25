package com.example.usuario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.dto.EstadoDeCuenta;
import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.service.CuentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cuenta Controller", description = "APIs para gestionar cuentas")
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Operation(summary = "Obtener todas las cuentas")
    @GetMapping
    public List<CuentaResponse> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @Operation(summary = "Obtener cuenta por ID")
    @GetMapping("/{id}")
    public CuentaResponse getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @Operation(summary = "Crear una cuenta")
    @PostMapping("/add")
    public ResponseEntity<String> createCuenta(@RequestBody CuentaRequest cuenta) {
        return cuentaService.addCuenta(cuenta);
    }

    @Operation(summary = "Actualizar una cuenta")
    @PutMapping("/update/{id}")
    public ResponseEntity<String>  updateCuenta(@PathVariable Long id, @RequestBody CuentaRequest cuenta) {
        return cuentaService.putCuenta(id, cuenta);
    }

    @Operation(summary = "Eliminar una cuenta")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteCuenta(@PathVariable Long id) {
        return cuentaService.deleteCuenta(id);
    }

    // REQUERIMIENTOS ENUNCIADO

    @Operation(summary = "Habilitar o anular una cuenta")
    @PatchMapping("/{id}/cambiar-estado")
    public ResponseEntity<String>  cambiarEstadoCuenta(@PathVariable Long id, @RequestBody EstadoDeCuenta estado) {
        return cuentaService.cambiarEstadoCuenta(id, estado);
    }

}
