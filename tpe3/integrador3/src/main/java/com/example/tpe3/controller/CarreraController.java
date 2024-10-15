package com.example.tpe3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tpe3.service.CarreraService;
import com.example.tpe3.dto.CarreraDto;
import com.example.tpe3.dto.ReporteCarreraDto;
import com.example.tpe3.model.Carrera;
import java.util.List;




@RestController
@RequestMapping("/carreras")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraService.saveCarrera(carrera);
    }
    //f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @GetMapping("/inscriptos")
    public List<CarreraDto> getCarrerasConEstudiantes(){
        return carreraService.getCarrerasConEstudiantes();
    }
    

    //h)
    @GetMapping("/reporte")
    public List<ReporteCarreraDto> generarReporteCarreras() {
        return carreraService.generarReporteCarreras();
    }
    
}