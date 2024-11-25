package com.example.viaje_service.service.impl;

import com.example.viaje_service.model.dto.*;
import com.example.viaje_service.model.entity.Precio;
import com.example.viaje_service.model.entity.Viaje;
import com.example.viaje_service.model.entity.Viaje.EstadoViaje;
import com.example.viaje_service.repository.PrecioRepository;
import com.example.viaje_service.repository.ViajeRepository;
import com.example.viaje_service.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> updateViaje(Long id, ViajeUpdateRequest viajeRequest) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);

        if(viaje != null){
            viaje.setKilometrosRecorridos(viajeRequest.getKilometrosRecorridos());
            viaje.setFechaFin(viajeRequest.getFechaFin());
            viaje.setEstado(viajeRequest.getEstado());
            viaje.setPrecioAplicado(calcularPrecio(viaje));
            viajeRepository.save(viaje);
            return ResponseEntity.status(HttpStatus.OK).body("Viaje actualizado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El viaje no existe");
    }

    @Override
    public ResponseEntity<String> deleteViaje(Long id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        if(viaje != null){
            viajeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Viaje eliminado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El viaje no existe");
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
