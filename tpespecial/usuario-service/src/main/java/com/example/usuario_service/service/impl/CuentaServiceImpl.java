package com.example.usuario_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.entity.Cuenta;
import com.example.usuario_service.repository.CuentaRepository;
import com.example.usuario_service.service.CuentaService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    //Buscar todos las cuentas
    @Override
    public List<CuentaResponse> getAllCuentas() {
        return cuentaRepository.getAllCuentas();
    }

    //Buscar una cuenta por id
    @Override
    public CuentaResponse getCuentaById(Long id) {
        Cuenta c = cuentaRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cuenta con id " + id + " no encontrado"));
        return new CuentaResponse(c.getId(), c.getMercadoPagoId(),c.getCreditos(), c.getFechaDeCreacion(), c.isHabilitada());
    }

    //Agregar una cuenta
    @Override
    public void addCuenta(CuentaRequest cuenta) {
       Cuenta c = new Cuenta(cuenta.getMercadoPagoId());
       cuentaRepository.save(c);
    }

    //Actualizar una cuenta
    @Override
    public void putCuenta(Long id, CuentaRequest cuenta) {
        Cuenta c = cuentaRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cuenta con id " + id + " no encontrado"));
        c.setMercadoPagoId(cuenta.getMercadoPagoId());
        c.setCreditos(cuenta.getCreditos());
        c.setFechaDeCreacion(cuenta.getFechaDeCreacion());
        c.setHabilitada(cuenta.isHabilitada());
        cuentaRepository.save(c);
    }


    //Eliminar una cuenta
    @Override
    public void deleteCuenta(Long id) {
        Cuenta c = cuentaRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cuenta con id " + id + " no encontrado"));
        
            cuentaRepository.delete(c);
    }

    @Override
    public void cambiarEstadoCuenta(Long id, boolean habilitada) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if(cuenta != null){
            cuenta.setHabilitada(habilitada);
        }
    }
 
}