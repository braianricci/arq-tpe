package com.example.tpe3.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tpe3.dto.EstudianteDto;
import com.example.tpe3.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT new com.example.tpe3.dto.EstudianteDto(e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, e.ciudad, e.numeroLibretaUniversitaria) FROM Estudiante e")
    List<EstudianteDto> findAllEstudiantesDto();

    @Query ("SELECT e FROM Estudiante e where e.numeroDocumento = :dni")
    Optional<Estudiante> findByDNI(String dni);


    //e) Recuperar todos los estudiantes, en base a su género.
    @Query("SELECT new com.example.tpe3.dto.EstudianteDto (e.nombres,e.apellido,e.edad,e.genero,e.numeroDocumento,e.ciudad,e.numeroLibretaUniversitaria) FROM Estudiante e WHERE e.genero =:genero")
    List<EstudianteDto> findByGenero(String genero);

    //d) Recuperar un estudiante, en base a su número de libreta universitaria.
    @Query("SELECT new com.example.tpe3.dto.EstudianteDto (e.nombres,e.apellido,e.edad,e.genero,e.numeroDocumento,e.ciudad,e.numeroLibretaUniversitaria) FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibretaUniversitaria")
    EstudianteDto findByNumeroLibretaUniversitaria(String numeroLibretaUniversitaria);

    //g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Query("SELECT new com.example.tpe3.dto.EstudianteDto (e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, e.ciudad, e.numeroLibretaUniversitaria) FROM Inscripcion i JOIN i.estudiante e JOIN i.carrera c WHERE c.nombre = :carrera AND e.ciudad = :ciudad")
    List<EstudianteDto> findByCarreraYCiudad(String carrera, String ciudad);

    //c) Recuperar todos los estudiantes, y especificar algun criterio de ordenamiento simple.
    @Query("SELECT new com.example.tpe3.dto.EstudianteDto(e.nombres, e.apellido, e.edad, e.genero, e.numeroDocumento, e.ciudad, e.numeroLibretaUniversitaria) FROM Estudiante e ORDER BY e.nombres")
    List<EstudianteDto> findAllOrderByName();

    
}