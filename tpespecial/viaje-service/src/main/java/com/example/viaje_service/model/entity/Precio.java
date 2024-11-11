package com.example.viaje_service.model.entity;

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
public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Double tarifaNormal;
    private Double tarifaPausaExtendida;
    private LocalDate fechaEfectiva;

    public Precio(Double tarifaNormal, Double tarifaPausaExtendida, LocalDate fechaEfectiva) {
        this.tarifaNormal = tarifaNormal;
        this.tarifaPausaExtendida = tarifaPausaExtendida;
        this.fechaEfectiva = fechaEfectiva;
    }
}
