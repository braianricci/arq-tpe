package com.example.DTO;

public class EstudianteDTO {

    private String nombres;
    private String apellido;
    private int edad;
    private String genero;
    private String numeroDocumento;
    private String ciudadResidencia;
    private String numeroLibretaUniversitaria;

    public EstudianteDTO(String nombres, String apellido, int edad, String genero, String numeroDocumento,
            String ciudadResidencia, String numeroLibretaUniversitaria) {

        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }

    @Override
    public String toString() {
        return "\n-" +
                nombres + " " +
                apellido + ", " +
                genero + ", DNI: " +
                numeroDocumento + ", reside en " +
                ciudadResidencia + ", libreta N°: " +
                numeroLibretaUniversitaria + ".";
    }
}
