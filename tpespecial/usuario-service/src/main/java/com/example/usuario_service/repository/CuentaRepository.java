package com.example.usuario_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

    @Query("SELECT new com.example.usuario_service.model.dto.CuentaResponse(c.id, c.mercadoPagoId, c.creditos, c.fechaDeCreacion, c.habilitada) FROM Cuenta c")
    List<CuentaResponse> getAllCuentas();



}