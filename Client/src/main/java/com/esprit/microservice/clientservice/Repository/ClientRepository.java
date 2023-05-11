package com.esprit.microservice.clientservice.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.microservice.clientservice.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from Client c where c.firstName like :firstName")
	public Page<Client> getClientByName(@Param("firstName") String reference, Pageable pageable);
	
	   
}
