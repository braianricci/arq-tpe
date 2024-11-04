package com.example.monopatin_service.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document
@Data
@NoArgsConstructor
public class Monopatin {
    
    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String modelo;
    private EstadoMonopatin estado;
    private Point ubicacion;
    private BigDecimal kilometrajeTotal;
    private Integer tiempoUsoTotal;
    private LocalDateTime ultimoMantenimiento;

    public Monopatin(String modelo) {
        this.modelo = modelo;
        this.estado = EstadoMonopatin.DISPONIBLE;
        this.ubicacion = null;
        this.kilometrajeTotal = BigDecimal.ZERO; 
        this.tiempoUsoTotal = 0; 
        this.ultimoMantenimiento = null;
    }

    public enum EstadoMonopatin {
        DISPONIBLE,
        EN_USO,
        EN_MANTENIMIENTO,
        FUERA_DE_SERVICIO
    }

}
