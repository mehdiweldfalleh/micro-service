package com.esprit.microservice.EntrepriseService;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.esprit.microservice.EntrepriseService.Entity.Entreprise;
import com.esprit.microservice.EntrepriseService.Repositories.EntrepriseRepository;

@SpringBootApplication
@EnableEurekaClient
public class EntrepriseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrepriseApplication.class, args);
	}
	
	

}
