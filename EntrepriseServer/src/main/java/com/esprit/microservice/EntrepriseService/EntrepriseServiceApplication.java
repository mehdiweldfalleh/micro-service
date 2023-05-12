package com.esprit.microservice.EntrepriseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EntrepriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrepriseServiceApplication.class, args);
	}

}
