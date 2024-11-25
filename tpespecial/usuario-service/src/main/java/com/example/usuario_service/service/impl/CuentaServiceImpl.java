package com.example.usuario_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.usuario_service.model.dto.CuentaRequest;
import com.example.usuario_service.model.dto.CuentaResponse;
import com.example.usuario_service.model.dto.EstadoDeCuenta;
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
    public ResponseEntity<String> addCuenta(CuentaRequest cuenta) {
       Cuenta c = new Cuenta(cuenta.getMercadoPagoId());
       cuentaRepository.save(c);
       return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creada exitosamente.");
    }

    //Actualizar una cuenta
    @Override
    public ResponseEntity<String> putCuenta(Long id, CuentaRequest cuenta) {
        Cuenta c = cuentaRepository.findById(id).orElse(null);
        if(c != null){
            c.setMercadoPagoId(cuenta.getMercadoPagoId());
            c.setCreditos(cuenta.getCreditos());
            c.setFechaDeCreacion(cuenta.getFechaDeCreacion());
            c.setHabilitada(cuenta.isHabilitada());
            cuentaRepository.save(c);
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta actualizada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta no existe.");
    }


    //Eliminar una cuenta
    @Override
    public ResponseEntity<String> deleteCuenta(Long id) {
        Cuenta c = cuentaRepository.findById(id).orElse(null);
            if(c != null){
                cuentaRepository.delete(c);
                return ResponseEntity.status(HttpStatus.OK).body("Cuenta eliminada exitosamente.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta no existe.");
    }

    @Override
    public ResponseEntity<String> cambiarEstadoCuenta(Long id, EstadoDeCuenta estado) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if(cuenta != null){
            cuenta.setHabilitada(estado.getHabilitada());
            return ResponseEntity.status(HttpStatus.OK).body("Estado de cuenta modificado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta no existe.");
    }
 
}