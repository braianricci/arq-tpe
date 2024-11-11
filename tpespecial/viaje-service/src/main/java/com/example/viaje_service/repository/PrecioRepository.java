package com.example.viaje_service.repository;

import com.example.viaje_service.model.entity.Precio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long> {
    Optional<Precio> findTopByFechaEfectivaLessThanEqualOrderByFechaEfectivaDesc(LocalDate date);
}
