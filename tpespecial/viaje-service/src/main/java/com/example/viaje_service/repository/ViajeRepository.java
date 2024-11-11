package com.example.viaje_service.repository;

import com.example.viaje_service.model.entity.Viaje;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v.monopatinId " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaInicio) = :anio " +
            "GROUP BY v.monopatinId " +
            "HAVING COUNT(v.id) > :viajesMinimos")
    List<String> obtenerMonopatinesConMasViajes(int viajesMinimos, int anio);

    @Query("SELECT SUM(CASE WHEN v.tipoTarifa = com.example.viaje_service.model.entity.Viaje.TipoTarifa.NORMAL " +
            "THEN p.tarifaNormal ELSE p.tarifaPausaExtendida END) " +
            "FROM Viaje v " +
            "JOIN v.precioAplicado p " +
            "WHERE v.estado = com.example.viaje_service.model.entity.Viaje.EstadoViaje.FINALIZADO " +
            "AND YEAR(v.fechaFin) = :anio " +
            "AND MONTH(v.fechaFin) BETWEEN :desdeMes AND :hastaMes")
    BigDecimal obtenerTotalFacturadoPorRangoDeMesesEnAnio(int anio, int desdeMes, int hastaMes);
}
