package com.example.tpe3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.tpe3.repository.CarreraRepository;
import com.example.tpe3.dto.CarreraDto;
import com.example.tpe3.dto.ReporteCarreraDto;
import com.example.tpe3.model.Carrera;



@Service
public class CarreraService {
    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }
    
    //f) Recuperar carreras con estudiantes inscriptos y ordenar por cantidad de inscriptos.
    public List<CarreraDto> getCarrerasConEstudiantes(){
        return carreraRepository.findCarrerasConEstudiantesInscriptosOrdenadas();
    }

    //h) Generar reporte carreras.
    public List<ReporteCarreraDto> generarReporteCarreras() {
        return carreraRepository.generarReporteCarreras();
    }
}