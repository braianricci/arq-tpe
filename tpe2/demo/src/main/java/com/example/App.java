package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.example.DTO.CarreraDTO;
import com.example.DTO.EstudianteDTO;
import com.example.Entidad.Carrera;
import com.example.Entidad.Estudiante;
import com.example.Repositorio.CarreraRepositoryImpl;
import com.example.Repositorio.EstudianteRepositoryImpl;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("registroEstudiantes");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Helper helper = new Helper(entityManager);

        EstudianteRepositoryImpl estudianteRepo = new EstudianteRepositoryImpl(entityManager);
        CarreraRepositoryImpl carreraRepo = new CarreraRepositoryImpl(entityManager);
        helper.inicializar(estudianteRepo, carreraRepo);

        try {

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * a) Dar de alta un estudiante
             */

            Estudiante nuevoEstudiante = new Estudiante("Gerardo", "Mancilla", 75, "masculino", "22562444", "Tandil",
                    "U072");
            entityManager.getTransaction().begin();
            estudianteRepo.altaEstudiante(nuevoEstudiante);
            entityManager.getTransaction().commit();

            String consignaA = "\na) Estudiante dado de alta: " + nuevoEstudiante.getNombres() + " "
                    + nuevoEstudiante.getApellido() + ".";

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * b) Matricular un estudiante en una carrera asumiendo que ya existe una
             * carrera con ID 1
             */

            entityManager.getTransaction().begin();
            estudianteRepo.matricularEstudiante(nuevoEstudiante.getId(), 1L);
            entityManager.getTransaction().commit();

            String consignaB = "\nb) Estudiante " + nuevoEstudiante.getNombres() + " " + nuevoEstudiante.getApellido()
                    + " matriculado en "
                    + entityManager.find(Carrera.class, 1L).getNombre() + " exitosamente.";

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * c) Recuperar todos los estudiantes
             */

            List<EstudianteDTO> todosEstudiantes = estudianteRepo.recuperarTodosEstudiantes();

            String consignaC = "\nc) Estudiantes en la BD, ordenados alfabeticamente por apellido:\n";
            for (EstudianteDTO e : todosEstudiantes) {
                consignaC += e.toString();
            }

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * d) Recuperar un estudiante por número de libreta
             */

            EstudianteDTO estudiantePorLibreta = estudianteRepo.recuperarEstudiantePorLibreta("U002");

            String consignaD = "\nd) Estudiante con numero de libreta U002:\n" + estudiantePorLibreta.toString();

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * e) Recuperar estudiantes por género
             */

            List<EstudianteDTO> estudiantesPorGenero = estudianteRepo.recuperarEstudiantesPorGenero("masculino");

            String consignaE = "\ne) Estudiantes en la DB, filtrados por genero masculino:\n";
            for (EstudianteDTO e : estudiantesPorGenero) {
                consignaE += e.toString();
            }

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * f) Recuperar carreras con estudiantes inscriptos y ordenar por cantidad de
             * inscriptos
             */

            List<CarreraDTO> carrerasConInscriptos = carreraRepo.recuperarCarrerasConInscriptos();

            String consignaF = "\nf) Carreras con estudiantes inscriptos, ordenadas por cantidad descendiente:\n";
            for (CarreraDTO c : carrerasConInscriptos) {
                consignaF += c.toString();
            }

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * g) Recuperar estudiantes por carrera y ciudad
             */

            List<EstudianteDTO> estudiantesPorCarreraYCiudad = estudianteRepo
                    .recuperarEstudiantesPorCarreraYCiudad(1L,
                            "Tandil");

            String consignaG = "\ng) Estudiantes filtrados por carrera de ID 1, residentes de Tandil:\n";
            for (EstudianteDTO e : estudiantesPorCarreraYCiudad) {
                consignaG += e.toString();
            }

            /*
             * ═════════════════════════════════════════════════════════════════════════════
             * ═
             * 3) Generar reporte de carreras
             */

            List<Object[]> reporteCarreras = carreraRepo.generarReporteCarreras();

            String consigna3 = "\n3) Reporte de carreras con inscriptos por año, mostrando graduados:\n";
            for (Object[] fila : reporteCarreras) {
                consigna3 += "\n-Carrera: " + fila[0] + ", Año: " + fila[1] + ", Inscriptos: " + fila[2]
                        + ", Graduados: " + fila[3] + ".";
            }

            String sep2 = "\n\n═══════════════════════════════════════════════════════════════════════════════════\n";
            System.out.println(sep2 + consignaA +
                    sep2 + consignaB +
                    sep2 + consignaC +
                    sep2 + consignaD +
                    sep2 + consignaE +
                    sep2 + consignaF +
                    sep2 + consignaG +
                    sep2 + consigna3 + sep2);

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}