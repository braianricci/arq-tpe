package com.example.tpe3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDto {

    private String nombre;
    private Long cantidadInscriptos;

    public CarreraDto(String nombre) {
        this.nombre = nombre;
    }

}
