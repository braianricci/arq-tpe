package com.example.tpe3;

import jakarta.annotation.PostConstruct;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.tpe3.util.CargaDeDatos;



@SpringBootApplication
public class Tpe3Application {
	
	@Autowired
	private CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(Tpe3Application.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		cargaDeDatos.cargarTodosLosDatos();
	}
}
