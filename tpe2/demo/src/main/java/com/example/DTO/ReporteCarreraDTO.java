package com.example.DTO;

public class ReporteCarreraDTO {

    private String nombre;
    private int anio;
    private long egresados;

    public ReporteCarreraDTO(String nombre, int anio, long egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "\nLa carrera " + nombre +
                ", en el año " + anio +
                ", tuvo " + egresados + ".";
    }
}
