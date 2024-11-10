package com.example.parada_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.example.parada_service.repository")
public class ParadaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadaServiceApplication.class, args);
	}

}
