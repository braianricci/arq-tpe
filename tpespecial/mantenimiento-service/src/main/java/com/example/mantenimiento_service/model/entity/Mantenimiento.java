package com.example.mantenimiento_service.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long idMonopatin;
    
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoMantenimiento estado;

    public Mantenimiento(Long idMonopatin){
        this.idMonopatin = idMonopatin;
        this.fechaInicio = LocalDate.now();
        this.estado = EstadoMantenimiento.EN_CURSO;
    }

    public enum EstadoMantenimiento {
        EN_CURSO,
        COMPLETADO,
        PENDIENTE
    }
}
