package com.example.Repositorio;

import java.util.List;

import com.example.DTO.CarreraDTO;

public interface CarreraRepository {

    public List<CarreraDTO> recuperarCarrerasConInscriptos();

    public List<Object[]> generarReporteCarreras();
}
