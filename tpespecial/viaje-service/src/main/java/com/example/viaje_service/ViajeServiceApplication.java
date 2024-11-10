package com.example.viaje_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ViajeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViajeServiceApplication.class, args);
	}

}
