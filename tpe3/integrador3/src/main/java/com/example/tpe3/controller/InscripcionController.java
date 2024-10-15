package com.example.tpe3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.tpe3.dto.InscripcionDto;
import com.example.tpe3.model.Inscripcion;
import com.example.tpe3.service.InscripcionService;
import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }


    // b) Matricular un estudiante en una carrera.
    @PostMapping("/matricular")
    public ResponseEntity<String> matricularEstudianteEnCarrera(@RequestBody InscripcionDto inscripcionDTO){
        // Llama al servicio para matricular al estudiante
        String response = inscripcionService.matricularEstudianteEnCarrera(inscripcionDTO);

        return ResponseEntity.ok(response);
    }

    // Otros endpoints
}
