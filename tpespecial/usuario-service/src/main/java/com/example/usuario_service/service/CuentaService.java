package com.example.usuario_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.dto.EstadoDeCuenta;



public interface CuentaService {

    List<CuentaResponse> getAllCuentas();
    CuentaResponse getCuentaById(Long id);
    ResponseEntity<String> addCuenta(CuentaRequest cuenta);
    ResponseEntity<String> putCuenta(Long id, CuentaRequest cuenta);
    ResponseEntity<String> deleteCuenta(Long id);
    ResponseEntity<String> cambiarEstadoCuenta(Long id, EstadoDeCuenta estado);

}