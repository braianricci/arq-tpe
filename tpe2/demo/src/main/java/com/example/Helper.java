package com.example;

import javax.persistence.EntityManager;

import com.example.Entidad.Carrera;
import com.example.Entidad.Estudiante;
import com.example.Repositorio.CarreraRepositoryImpl;
import com.example.Repositorio.EstudianteRepositoryImpl;

public class Helper {
    private EntityManager entityManager;

    public Helper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inicializar(EstudianteRepositoryImpl estudianteRepo, CarreraRepositoryImpl carreraRepo) {

        Carrera sistemas = new Carrera("Ingenieria en sistemas");
        Carrera matematicas = new Carrera("Lic. Cs. Matematicas");
        Carrera tudai = new Carrera("TUDAI");
        Carrera fisica = new Carrera("Profesorado de Fisica");

        Estudiante nuevoEstudiante = new Estudiante("Pedro", "Gonzales", 20, "masculino", "33543234",
                "Azul",
                "U002");
        Estudiante nuevoEstudiante1 = new Estudiante("Luis", "Rodriguez", 25, "masculino", "32674812",
                "Tandil",
                "U003");
        Estudiante nuevoEstudiante2 = new Estudiante("Marcos", "Villa", 22, "masculino", "31533254",
                "Tandil",
                "U004");
        Estudiante nuevoEstudiante3 = new Estudiante("Damian", "Gonzales", 20, "masculino", "33234234",
                "Tandil",
                "U005");
        Estudiante nuevoEstudiante4 = new Estudiante("Marta", "Alvares", 20, "femenino", "33543234",
                "Azul",
                "U006");
        Estudiante nuevoEstudiante5 = new Estudiante("Susana", "Gimenez", 25, "femenino", "33543234",
                "Tandil",
                "U007");
        Estudiante nuevoEstudiante6 = new Estudiante("Mirta", "Legrand", 999, "femenino", "00000001",
                "Tandil",
                "U008");

        entityManager.getTransaction().begin();

        carreraRepo.altaCarrera(sistemas);
        carreraRepo.altaCarrera(matematicas);
        carreraRepo.altaCarrera(tudai);
        carreraRepo.altaCarrera(fisica);

        estudianteRepo.altaEstudiante(nuevoEstudiante);
        estudianteRepo.altaEstudiante(nuevoEstudiante1);
        estudianteRepo.altaEstudiante(nuevoEstudiante2);
        estudianteRepo.altaEstudiante(nuevoEstudiante3);
        estudianteRepo.altaEstudiante(nuevoEstudiante4);
        estudianteRepo.altaEstudiante(nuevoEstudiante5);
        estudianteRepo.altaEstudiante(nuevoEstudiante6);

        estudianteRepo.matricularEstudiante(nuevoEstudiante.getId(), 1L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante.getId(), 3L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante.getId(), 4L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante2.getId(), 1L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante2.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante2.getId(), 3L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante2.getId(), 4L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante3.getId(), 1L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante3.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante3.getId(), 3L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante3.getId(), 4L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante4.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante4.getId(), 3L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante4.getId(), 4L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante5.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante5.getId(), 4L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante6.getId(), 1L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante6.getId(), 2L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante6.getId(), 3L);
        estudianteRepo.matricularEstudiante(nuevoEstudiante6.getId(), 4L);

        entityManager.getTransaction().commit();
    }
}
