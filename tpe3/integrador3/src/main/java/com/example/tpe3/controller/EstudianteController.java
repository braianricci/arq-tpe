package com.example.tpe3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tpe3.dto.EstudianteDto;
import com.example.tpe3.model.Estudiante;
import com.example.tpe3.service.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;


    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        // Sin cambios en el retorno para asegurarte que no haya problemas de serialización.
        return estudianteService.getAllEstudiantes();
    }


    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    //a) Dar de alta un estudiante
    @PostMapping("/insert")
    public EstudianteDto createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.altaEstudiante(estudiante);
    }

    //c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @GetMapping("/ordenadosPorNombre")
    public List<EstudianteDto> getAllEstudiantesOrderByName(){
        return estudianteService.getEstudiantesOrderByName();
    }

    //d) Recuperar un estudiante, en base a su número de libreta universitaria.
    @GetMapping("/libreta/{lu}")
    public EstudianteDto getEstudianteByLibreta(@PathVariable String lu){
       return estudianteService.getEstudianteByLibreta(lu);
    }

    //e) Recuperar todos los estudiantes, en base a su género.
    @GetMapping("/genero/{genero}")
    public List<EstudianteDto> getStudentByGender(@PathVariable String genero){
        return estudianteService.getEstudiantesByGenero(genero);
    }

    //g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @GetMapping("/{carrera}/{ciudad}")
    public List<EstudianteDto> getStudentByCareerAndCity(@PathVariable String carrera, @PathVariable String ciudad){
        return estudianteService.getEstudiantesByCareerAndCity(carrera, ciudad);
    }







    // Otros endpoints
}
