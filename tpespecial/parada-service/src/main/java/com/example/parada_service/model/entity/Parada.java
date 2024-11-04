package com.example.parada_service.model.entity;

import java.util.ArrayList;
import java.util.List;

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
public class Parada {
    
    @Id
    @Setter(AccessLevel.NONE)
    private String id;


    private String nombre;
    private Point ubicacion;
    private List<String> monopatines; // Almacena los IDs de los monopatines
    

    public Parada(String nombre, Point ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        monopatines = new ArrayList<>();
    }

    public void addMonopatin(String monopatinId){
        if (!monopatines.contains(monopatinId)) {
            monopatines.add(monopatinId); 
        }
    }
}
