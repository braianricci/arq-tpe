package com.example.tpe3.dto;

import com.example.tpe3.model.Carrera;
import com.example.tpe3.model.Estudiante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDto {

    private Estudiante estudiante;
    private Carrera carrera;
    
}
