package com.example.Repositorio;

import java.util.List;

import com.example.DTO.CarreraDTO;
import com.example.Entidad.Carrera;

public interface CarreraRepository {

    public void altaCarrera(Carrera carrera);

    public List<CarreraDTO> recuperarCarrerasConInscriptos();

    public List<Object[]> generarReporteCarreras();
}
