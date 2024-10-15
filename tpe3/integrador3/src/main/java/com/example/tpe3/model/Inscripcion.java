package com.example.tpe3.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;


@Entity
@Data // Lombok generará getters, setters, toString, equals y hashCode
public class Inscripcion {

    @Id
    @Setter(AccessLevel.NONE) // No generará un setter para id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonBackReference
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    @JsonBackReference
    private Carrera carrera;

    @Column
    private boolean graduado;

    @Column
    private LocalDate fechaInscripcion;

    @Column
    private LocalDate fechaEgreso; // Puede ser null si no ha egresado


    public Inscripcion(){
       
    }

    public Inscripcion(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaInscripcion = LocalDate.now();
        this.fechaEgreso = null;
    }

    // Metodo para determinar si el estudiante se ha graduado
    public boolean isGraduado() {
        return fechaEgreso != null;  // Retorna true si la fecha de egreso no es null
    }

    
        


    
    
}
