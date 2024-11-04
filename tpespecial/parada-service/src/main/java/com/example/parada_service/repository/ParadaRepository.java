package com.example.parada_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.parada_service.model.entity.Parada;

public interface ParadaRepository extends MongoRepository<Parada, String> {
    
}
