package com.example.usuario_service.model.dto;

import org.springframework.data.geo.Point;

import lombok.Data;

@Data
public class MonopatinesCercanosDTO {
    
    private String id;
    private Point ubicacion;
}
