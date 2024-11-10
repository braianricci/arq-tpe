package com.example.mantenimiento_service.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaInicio;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaFin;
    private EstadoMantenimiento estado;

    public enum EstadoMantenimiento {
        EN_CURSO,
        COMPLETADO,
        PENDIENTE
    }
}
