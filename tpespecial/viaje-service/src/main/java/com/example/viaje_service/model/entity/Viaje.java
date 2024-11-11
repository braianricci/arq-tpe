package com.example.viaje_service.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long usuarioId;
    private String monopatinId;
    
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaInicio;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaFin;

    private BigDecimal kilometrosRecorridos;
    private EstadoViaje estado;
    private Long paradaInicioId;
    private Long paradaFinId;
    private BigDecimal tarifaAplicada;
    private TipoTarifa tipoTarifa;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaPausaInicio;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime fechaPausaFin;

    public Viaje(Long usuario_id, String monopatin_id, Long paradaInicioId, BigDecimal tarifaAplicada) {
        this.usuarioId = usuario_id;
        this.monopatinId = monopatin_id;
        this.paradaInicioId = paradaInicioId;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaInicio = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.estado = EstadoViaje.ACTIVO;
        this.kilometrosRecorridos = BigDecimal.ZERO;
        this.tipoTarifa = TipoTarifa.NORMAL;
    }

    // Métodos adicionales para manejar pausas
    public void iniciarPausa() {
        this.fechaPausaInicio = LocalDateTime.now();
    }

    public void finalizarPausa() {
        this.fechaPausaFin = LocalDateTime.now();
    }

    public enum EstadoViaje {
        ACTIVO,
        FINALIZADO,
        PAUSADO
    }

    public enum TipoTarifa {
        NORMAL,
        PAUSA_EXTENDIDA
    }

}
