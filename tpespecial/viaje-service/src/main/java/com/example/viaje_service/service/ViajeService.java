package com.example.viaje_service.service;

import java.math.BigDecimal;
import java.util.List;
import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.model.entity.Precio;
import com.example.viaje_service.model.entity.Viaje;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ViajeService {

    // Métodos CRUD
    List<ViajeResponse> getAllViajes();

    ViajeResponse getViajeById(Long id);

    ViajeResponse addViaje(ViajeCreateRequest viaje);

    ResponseEntity<String> updateViaje(Long id, ViajeUpdateRequest viaje);

    ResponseEntity<String> deleteViaje(Long id);

    List<String> obtenerMonopatinesConMasViajes(int viajesMinimos, int anio);

    BigDecimal obtenerTotalFacturadoPorRangoDeMesesEnAnio(int year, int startMonth, int endMonth);

    PrecioResponse ajustarPrecios(AjustePreciosRequest request);

    Precio calcularPrecio(Viaje viaje);
}
