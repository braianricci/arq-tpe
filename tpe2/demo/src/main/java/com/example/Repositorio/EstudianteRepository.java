package com.example.Repositorio;

import java.util.List;

import com.example.DTO.EstudianteDTO;
import com.example.Entidad.Estudiante;

public interface EstudianteRepository {

    // a) Dar de alta un estudiante
    public void altaEstudiante(Estudiante estudiante);

    // b) Matricular un estudiante en una carrera
    public void matricularEstudiante(Long estudianteId, Long carreraId);

    // c) Recuperar todos los estudiantes, ordenados por apellido
    public List<EstudianteDTO> recuperarTodosEstudiantes();

    // d) Recuperar un estudiante, en base a su número de libreta universitaria
    public EstudianteDTO recuperarEstudiantePorLibreta(String numeroLibreta);

    // e) Recuperar todos los estudiantes, en base a su género
    public List<EstudianteDTO> recuperarEstudiantesPorGenero(String genero);

    // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad
    // de residencia
    public List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(Long carreraId, String ciudad);
}
