package com.example.DTO;

public class CarreraDTO {
    private String nombre;
    private long inscriptos;

    public CarreraDTO(String nombre, long inscriptos) {
        this.nombre = nombre;
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return "\n-" + nombre + ", N° de inscriptos: " + inscriptos + ".";
    }
}
