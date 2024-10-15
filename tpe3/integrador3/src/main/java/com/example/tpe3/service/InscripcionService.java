package com.example.tpe3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tpe3.dto.InscripcionDto;
import com.example.tpe3.model.Carrera;
import com.example.tpe3.model.Estudiante;
import com.example.tpe3.model.Inscripcion;
import com.example.tpe3.repository.CarreraRepository;
import com.example.tpe3.repository.EstudianteRepository;
import com.example.tpe3.repository.InscripcionRepository;

import jakarta.transaction.Transactional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    // b) Matricular un estudiante en una carrera.
    @Transactional
    public String matricularEstudianteEnCarrera(InscripcionDto inscripcionDTO) {
        Estudiante estudiante = inscripcionDTO.getEstudiante();
        Carrera carrera = inscripcionDTO.getCarrera();

        // Verifica que el estudiante y la carrera existen en la base de datos
        estudiante = estudianteRepository.findById(estudiante.getId())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        carrera = carreraRepository.findById(carrera.getId())
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));

        // Crear una nueva inscripción
        Inscripcion nuevaInscripcion = new Inscripcion(estudiante, carrera);

        // Actualizar las listas en memoria
        estudiante.addInscripcion(nuevaInscripcion);
        carrera.addInscripcion(nuevaInscripcion);

        // Guardar la inscripción en la base de datos
        inscripcionRepository.save(nuevaInscripcion);

        return "El estudiante " + estudiante.getNombres() + " fue matriculado en la carrera " + carrera.getNombre();
    }
}