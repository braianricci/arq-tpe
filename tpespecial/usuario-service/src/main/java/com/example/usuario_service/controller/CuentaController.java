package com.example.usuario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("")
    public List<CuentaResponse> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public CuentaResponse getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @PostMapping("/add")
    public void createCuenta(@RequestBody CuentaRequest cuenta) {
        this.cuentaService.addCuenta(cuenta);
    }

    @PutMapping("/update/{id}")
    public void updateCuenta(@PathVariable Long id, @RequestBody CuentaRequest cuenta) {
        this.cuentaService.putCuenta(id, cuenta);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        this.cuentaService.deleteCuenta(id);
    }

    // REQUERIMIENTOS ENUNCIADO

    // Endpoint para habilitar o anular una cuenta
    @PatchMapping("/{id}/cambiar-estado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiarEstadoCuenta(@PathVariable Long id, @RequestBody boolean habilitada) {
        cuentaService.cambiarEstadoCuenta(id, habilitada);
    }

}
