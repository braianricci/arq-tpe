package com.example.monopatin_service.model.dto;

import org.springframework.data.geo.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinesCercanosDTO {
    
    private String id;
    private Point ubicacion;
}
