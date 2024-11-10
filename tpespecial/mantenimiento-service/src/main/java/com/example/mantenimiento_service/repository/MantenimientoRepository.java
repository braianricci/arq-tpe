package com.example.mantenimiento_service.repository;

import com.example.mantenimiento_service.model.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

}
