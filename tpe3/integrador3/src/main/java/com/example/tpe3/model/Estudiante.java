package com.example.tpe3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data // Lombok generará getters, setters, toString, equals y hashCode
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private String nombres;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private String numeroDocumento;

    @Column
    private String ciudad;

    @Column
    private String numeroLibretaUniversitaria;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)  //Revisar cascadeType.PERSIST
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // Constructor
    public Estudiante (){

    }

    public Estudiante(String nombres, String apellido, int edad, String genero, String numeroDocumento,
            String ciudad, String numeroLibretaUniversitaria) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudad = ciudad;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }
    
    public void addInscripcion(Inscripcion i){
        this.inscripciones.add(i);
    }
}
