package com.example.Repositorio;

import java.util.List;

import javax.persistence.*;

import com.example.DTO.CarreraDTO;
import com.example.Entidad.Carrera;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager entityManager;

    public CarreraRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void altaCarrera(Carrera carrera) {
        this.entityManager.persist(carrera);
    }

    // f) Recuperar las carreras con estudiantes inscriptos, ordenadas por cantidad
    // de inscriptos
    public List<CarreraDTO> recuperarCarrerasConInscriptos() {
        TypedQuery<CarreraDTO> query = this.entityManager.createQuery(
                "SELECT new com.example.DTO.CarreraDTO(c.nombre, COUNT(i)) " +
                        "FROM Carrera c " +
                        "JOIN c.inscripciones i " +
                        "GROUP BY c.nombre " +
                        "ORDER BY COUNT(i) DESC",
                CarreraDTO.class);
        return query.getResultList();
    }

    // 3) Generar reporte de carreras
    public List<Object[]> generarReporteCarreras() {
        return this.entityManager.createQuery(
                "SELECT c.nombre, YEAR(i.fechaInscripcion), COUNT(DISTINCT i.estudiante), " +
                        "SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) " +
                        "FROM Carrera c LEFT JOIN c.inscripciones i " +
                        "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
                        "ORDER BY c.nombre, YEAR(i.fechaInscripcion)",
                Object[].class).getResultList();
    }
}
