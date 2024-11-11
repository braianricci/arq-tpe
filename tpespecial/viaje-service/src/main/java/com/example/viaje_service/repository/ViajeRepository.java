package com.example.viaje_service.repository;

import com.example.viaje_service.model.entity.Viaje;

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
    List<String>obtenerMonopatinesConMasViajes(int viajesMinimos, int anio);
}
