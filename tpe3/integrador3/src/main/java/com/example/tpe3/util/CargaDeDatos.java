package com.example.tpe3.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.example.tpe3.model.Carrera;
import com.example.tpe3.model.Estudiante;
import com.example.tpe3.model.Inscripcion;
import com.example.tpe3.repository.CarreraRepository;
import com.example.tpe3.repository.EstudianteRepository;
import com.example.tpe3.repository.InscripcionRepository;

@Component
public class CargaDeDatos {

    private final CarreraRepository carreraRepo;
    private final EstudianteRepository estudianteRepo;
    private final InscripcionRepository inscripRepo;

    
    public CargaDeDatos(CarreraRepository carreraRepo, EstudianteRepository estudianteRepo,
            InscripcionRepository inscripRepo) {
        this.carreraRepo = carreraRepo;
        this.estudianteRepo = estudianteRepo;
        this.inscripRepo = inscripRepo;
    }

    // Método para cargar todos los datos
    public void cargarTodosLosDatos() throws IOException {
        cargarCarrerasDesdeCSV();
        cargarEstudiantesDesdeCSV();
        cargarInscripcionesDesdeCSV();
    }

    // Carga carreras desde el CSV
    public void cargarCarrerasDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/example/tpe3/csv/carreras.csv");

        try (FileReader reader = new FileReader(archivoCSV);
                CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Carrera carrera = new Carrera(csvRecord.get("nombre"), csvRecord.get("duracion"));
                carreraRepo.save(carrera);
            }
        }
    }

    // Carga estudiantes desde el CSV
    public void cargarEstudiantesDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/example/tpe3/csv/estudiantes.csv");
        try (FileReader reader = new FileReader(archivoCSV);
                CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                // Crear el estudiante directamente con los parámetros del CSV
                Estudiante estudiante = new Estudiante(
                        csvRecord.get("nombre"),
                        csvRecord.get("apellido"),
                        Integer.parseInt(csvRecord.get("edad")),
                        csvRecord.get("genero"),
                        csvRecord.get("DNI"),
                        csvRecord.get("ciudad"),
                        csvRecord.get("LU"));
                 // Guardar el estudiante en la base de datos
                estudianteRepo.save(estudiante);
            }

        }
    }

    // Carga la relación entre estudiantes y carreras desde el CSV
    public void cargarInscripcionesDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/example/tpe3/csv/inscripciones.csv");
        try (FileReader reader = new FileReader(archivoCSV);
                CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                // Obtenemos los IDs del CSV
                String estudianteId = csvRecord.get("id_estudiante");
                Long carreraId = Long.parseLong(csvRecord.get("id_carrera"));

                // Buscamos el estudiante y la carrera en los repositorios
                Estudiante estudiante = estudianteRepo.findByDNI(estudianteId)
                        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + estudianteId));
                Carrera carrera = carreraRepo.findById(carreraId)
                        .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada: " + carreraId));

                // Creamos una nueva inscripción y asignamos el estudiante y la carrera
                Inscripcion inscripcion = new Inscripcion(estudiante, carrera);

                // Guardamos la inscripción
                inscripRepo.save(inscripcion);
            }
        }
    }

}
