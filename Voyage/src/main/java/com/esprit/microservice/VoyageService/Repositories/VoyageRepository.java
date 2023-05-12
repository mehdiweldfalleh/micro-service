package com.esprit.microservice.VoyageService.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.microservice.VoyageService.Entity.Voyage;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
	@Query("select v from Voyage v where v.reference like :reference")
	public Page<Voyage> getVoyageByReference(@Param("reference") String reference, Pageable pageable);
	   
}
