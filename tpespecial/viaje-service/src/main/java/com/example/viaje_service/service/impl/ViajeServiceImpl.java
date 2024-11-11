package com.example.viaje_service.service.impl;

import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.model.entity.Precio;
import com.example.viaje_service.model.entity.Viaje;
import com.example.viaje_service.model.entity.Viaje.EstadoViaje;
import com.example.viaje_service.repository.PrecioRepository;
import com.example.viaje_service.repository.ViajeRepository;
import com.example.viaje_service.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private PrecioRepository precioRepository;

    // CRUD Básico

    @Override
    public List<ViajeResponse> getAllViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream().map(this::convertToDto).toList();
    }

    @Override
    public ViajeResponse getViajeById(Long id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        return convertToDto(viaje);
    }

    @Override
    public ViajeResponse addViaje(ViajeCreateRequest viajeRequest) {
        Viaje viaje = new Viaje();
        viaje.setUsuarioId(viajeRequest.getUsuarioId());
        viaje.setMonopatinId(viajeRequest.getMonopatinId());
        viaje.setFechaInicio(LocalDateTime.now());
        viaje.setEstado(EstadoViaje.ACTIVO);

        viaje.setPrecioAplicado(calcularPrecio(viaje));

        viajeRepository.save(viaje);
        return convertToDto(viaje);
    }

    @Override
    public void updateViaje(Long id, ViajeUpdateRequest viajeRequest) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        viaje.setKilometrosRecorridos(viajeRequest.getKilometrosRecorridos());
        viaje.setFechaFin(viajeRequest.getFechaFin());
        viaje.setEstado(viajeRequest.getEstado());
        viaje.setPrecioAplicado(calcularPrecio(viaje));
        viajeRepository.save(viaje);
    }

    @Override
    public void deleteViaje(Long id) {
        viajeRepository.deleteById(id);
    }

    // Método auxiliar para convertir entidad a DTO
    private ViajeResponse convertToDto(Viaje viaje) {
        return new ViajeResponse(
                viaje.getId(),
                viaje.getUsuarioId(),
                viaje.getMonopatinId(),
                viaje.getFechaInicio(),
                viaje.getFechaFin(),
                viaje.getKilometrosRecorridos(),
                viaje.getParadaInicioId(),
                viaje.getParadaFinId(),
                viaje.getEstado(),
                viaje.getPrecioAplicado());
    }

    @Override
    public List<String> obtenerMonopatinesConMasViajes(int viajesMinimos, int anio) {
        return viajeRepository.obtenerMonopatinesConMasViajes(viajesMinimos, anio);
    }

    @Override
    public BigDecimal obtenerTotalFacturadoPorRangoDeMesesEnAnio(int anio, int desdeMes, int hastaMes) {
        return viajeRepository.obtenerTotalFacturadoPorRangoDeMesesEnAnio(anio, desdeMes, hastaMes);
    }

    @Override
    public PrecioResponse ajustarPrecios(AjustePreciosRequest request) {

        Precio precio = new Precio(
                request.getNuevaTarifaNormal(),
                request.getNuevaTarifaPausaExtendida(),
                request.getFechaEfectiva());
        precioRepository.save(precio);

        return new PrecioResponse(
                precio.getTarifaNormal(),
                precio.getTarifaPausaExtendida(),
                precio.getFechaEfectiva());
    }

    @Override
    public Precio calcularPrecio(Viaje viaje) {
        LocalDate fechaViaje = viaje.getFechaInicio().toLocalDate();
        return precioRepository.findTopByFechaEfectivaLessThanEqualOrderByFechaEfectivaDesc(fechaViaje)
                .orElseThrow(() -> new RuntimeException("No se encontró una tarifa para la fecha especificada"));
    }
}
