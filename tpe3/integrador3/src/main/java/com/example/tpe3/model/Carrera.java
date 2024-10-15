package com.example.tpe3.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;


@Entity
@Data // Lombok generará getters, setters, toString, equals y hashCode
public class Carrera {
    @Id
    @Setter(AccessLevel.NONE) // No generará un setter para id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre; //Tendra getter y setter generados por @Data

    @Column
    private String duracion;

    @Setter(AccessLevel.NONE) // No generará un setter para inscripciones.
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Inscripcion> inscripciones = new ArrayList<>();
    

    //Constructor
    public Carrera(){
    }

    public Carrera(String nombre, String duracion){
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public void addInscripcion(Inscripcion i){
        inscripciones.add(i);
    }
 
}
