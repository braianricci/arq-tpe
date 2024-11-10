package com.example.usuario_service.service;

import java.util.List;

import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.model.dto.CuentaResponse;



public interface CuentaService {

    List<CuentaResponse> getAllCuentas();
    CuentaResponse getCuentaById(Long id);
    void addCuenta(CuentaRequest cuenta);
    void putCuenta(Long id, CuentaRequest cuenta);
    void deleteCuenta(Long id);
    void anularCuenta(Long id);

}