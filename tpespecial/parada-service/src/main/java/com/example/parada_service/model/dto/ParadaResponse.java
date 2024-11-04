package com.example.parada_service.model.dto;

import org.springframework.data.geo.Point;
import java.util.List;

import lombok.Data;

@Data
public class ParadaResponse {
    
    private String id;
    private String nombre;
    private Point ubicacion;
    private List<String> monopatines; // Almacena los IDs de los monopatines

    // Constructor
    public ParadaResponse(String id, String nombre, Point ubicacion, List<String> monopatines) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.monopatines = monopatines;
    }
}