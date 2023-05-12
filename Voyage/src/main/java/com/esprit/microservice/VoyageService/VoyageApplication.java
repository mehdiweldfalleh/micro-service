package com.esprit.microservice.VoyageService;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.esprit.microservice.VoyageService.Entity.Voyage;
import com.esprit.microservice.VoyageService.Repositories.VoyageRepository;

@SpringBootApplication
@EnableEurekaClient
public class VoyageApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoyageApplication.class, args);
	}
	
	 @Bean
	    ApplicationRunner init(VoyageRepository repository) {
	        return args -> {
	            Stream.of("RZMG96", "RZMG97", "RZMG98").forEach(reference -> {
	                repository.save(new Voyage(reference));
	            });
	            repository.findAll().forEach(System.out::println);
	        };
	    }

}
