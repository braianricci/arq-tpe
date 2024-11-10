package com.example.monopatin_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.monopatin_service.model.entity.Monopatin;


public interface MonopatinRepository extends MongoRepository<Monopatin, String>{

    Optional<Monopatin> findByModelo(String modelo);

    List<Monopatin> findByUbicacionNear(Point punto, Distance distancia);

}
