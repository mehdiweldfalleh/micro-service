package com.esprit.microservice.EntrepriseService.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.microservice.EntrepriseService.Entity.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
	@Query("select e from Entreprise e where e.nom like :nom")
	public Page<Entreprise> getEntrepriseByName(@Param("nom") String nom, Pageable pageable);
	   
}
