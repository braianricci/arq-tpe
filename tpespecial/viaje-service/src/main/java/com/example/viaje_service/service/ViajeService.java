package com.example.viaje_service.service;

import java.math.BigDecimal;
import java.util.List;
import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.model.entity.Precio;
import com.example.viaje_service.model.entity.Viaje;

import org.springframework.stereotype.Service;

@Service
public interface ViajeService {

    // Métodos CRUD
    List<ViajeResponse> getAllViajes();

    ViajeResponse getViajeById(Long id);

    ViajeResponse addViaje(ViajeCreateRequest viaje);

    void updateViaje(Long id, ViajeUpdateRequest viaje);

    void deleteViaje(Long id);

    List<String> obtenerMonopatinesConMasViajes(int viajesMinimos, int anio);

    BigDecimal obtenerTotalFacturadoPorRangoDeMesesEnAnio(int year, int startMonth, int endMonth);

    void ajustarPrecios(AjustePreciosDTO request);

    Precio calcularPrecio(Viaje viaje);
}
