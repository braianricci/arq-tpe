package com.example.tpe3.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tpe3.repository.EstudianteRepository;
import com.example.tpe3.dto.EstudianteDto;
import com.example.tpe3.model.Estudiante;


@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    //a) Dar de alta un estudiante
    public EstudianteDto altaEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return new EstudianteDto(estudiante.getNombres(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getNumeroDocumento(), estudiante.getCiudad(), estudiante.getNumeroLibretaUniversitaria());
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }
    
    //c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    public List<EstudianteDto> getEstudiantesOrderByName(){
        return estudianteRepository.findAllOrderByName();
    }

    //d) Recuperar un estudiante, en base a su número de libreta universitaria.
    public EstudianteDto getEstudianteByLibreta(String libreta){
        return estudianteRepository.findByNumeroLibretaUniversitaria(libreta);
    }


    //e) Recuperar todos los estudiantes, en base a su género.
    public List<EstudianteDto> getEstudiantesByGenero(String genero) {
        return estudianteRepository.findByGenero(genero); 
    }

    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Estudiante con id " + id + " no encontrado"));
    }

    //g Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<EstudianteDto> getEstudiantesByCareerAndCity(String carrera, String ciudad){
        return estudianteRepository.findByCarreraYCiudad(carrera, ciudad);
    }

}