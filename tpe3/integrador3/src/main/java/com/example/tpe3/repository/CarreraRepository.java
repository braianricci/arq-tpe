package com.example.tpe3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tpe3.dto.CarreraDto;
import com.example.tpe3.dto.ReporteCarreraDto;
import com.example.tpe3.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    //f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @Query("SELECT new com.example.tpe3.dto.CarreraDto(c.nombre, COUNT(i)) " +
    "FROM Carrera c " +
    "JOIN c.inscripciones i " +
    "GROUP BY c.id, c.nombre " +
    "HAVING COUNT(i) > 0 " +
    "ORDER BY COUNT(i) DESC")
    List<CarreraDto> findCarrerasConEstudiantesInscriptosOrdenadas();

    
    //h) Generar un reporte de las carreras, que para c/carrera incluya inf. de los inscripctos y egresador por año.
    //   Ordenar las carreras alfabeticamente, y presentar los años de manera cronologica.
    @Query("SELECT new com.example.tpe3.dto.ReporteCarreraDto(c.nombre, YEAR(i.fechaInscripcion), " +
            "COUNT(i), SUM(CASE WHEN i.fechaEgreso IS NOT NULL THEN 1 ELSE 0 END)) " +
            "FROM Carrera c " +
            "JOIN Inscripcion i ON i.carrera.id = c.id " +
            "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
            "ORDER BY c.nombre ASC, YEAR(i.fechaInscripcion) ASC")
    List<ReporteCarreraDto> generarReporteCarreras();
}