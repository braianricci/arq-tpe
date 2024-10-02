package com.example.Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

import com.example.DTO.EstudianteDTO;
import com.example.Entidad.Carrera;
import com.example.Entidad.Estudiante;
import com.example.Entidad.Inscripcion;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private EntityManager entityManager;

    public EstudianteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // a) Dar de alta un estudiante
    public void altaEstudiante(Estudiante estudiante) {
        this.entityManager.persist(estudiante);
    }

    // b) Matricular un estudiante en una carrera
    public void matricularEstudiante(Long estudianteId, Long carreraId) {
        // Buscar estudiante y carrera por sus IDs
        Estudiante estudiante = this.entityManager.find(Estudiante.class, estudianteId);
        Carrera carrera = this.entityManager.find(Carrera.class, carreraId);

        // Crear una nueva inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCarrera(carrera);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setGraduado(false);

        // Agregar la inscripción a la lista de inscripciones del estudiante
        estudiante.getInscripciones().add(inscripcion);

        // Persistir la Inscripcion
        this.entityManager.persist(inscripcion);
    }

    // c) Recuperar todos los estudiantes, ordenados por apellido
    public List<EstudianteDTO> recuperarTodosEstudiantes() {
        TypedQuery<EstudianteDTO> query = this.entityManager.createQuery(
                "SELECT new com.example.DTO.EstudianteDTO(" +
                        "e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, " +
                        "e.ciudadResidencia, e.numeroLibretaUniversitaria) " +
                        "FROM Estudiante e ORDER BY e.apellido",
                EstudianteDTO.class);
        return query.getResultList();
    }

    // d) Recuperar un estudiante, en base a su número de libreta universitaria
    public EstudianteDTO recuperarEstudiantePorLibreta(String numeroLibreta) {
        TypedQuery<EstudianteDTO> query = this.entityManager
                .createQuery(
                        "SELECT new com.example.DTO.EstudianteDTO(" +
                                "e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, " +
                                "e.ciudadResidencia, e.numeroLibretaUniversitaria) " +
                                "FROM Estudiante e " +
                                "WHERE e.numeroLibretaUniversitaria = :numeroLibreta",
                        EstudianteDTO.class);
        query.setParameter("numeroLibreta", numeroLibreta);
        return query.getSingleResult();
    }

    // e) Recuperar todos los estudiantes, en base a su género
    public List<EstudianteDTO> recuperarEstudiantesPorGenero(String genero) {
        TypedQuery<EstudianteDTO> query = this.entityManager.createQuery(
                "Select new com.example.DTO.EstudianteDTO(" +
                        "e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, " +
                        "e.ciudadResidencia, e.numeroLibretaUniversitaria) " +
                        "FROM Estudiante e " +
                        "WHERE e.genero = :genero",
                EstudianteDTO.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

    // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad
    // de residencia
    public List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(Long carreraId, String ciudad) {
        TypedQuery<EstudianteDTO> query = this.entityManager.createQuery(
                "Select new com.example.DTO.EstudianteDTO(" +
                        "e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, " +
                        "e.ciudadResidencia, e.numeroLibretaUniversitaria) " +
                        "FROM Estudiante e " +
                        "JOIN e.inscripciones i " +
                        "WHERE i.carrera.id = :carreraId " +
                        "AND e.ciudadResidencia = :ciudad",
                EstudianteDTO.class);
        query.setParameter("carreraId", carreraId);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }
}
