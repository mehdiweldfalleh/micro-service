package com.esprit.microservice.EntrepriseService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.microservice.EntrepriseService.Entity.Entreprise;
import com.esprit.microservice.EntrepriseService.Services.EntrepriseService;

@RestController
@RequestMapping(value = "/entreprise")
public class EntrepriseController {

	@Autowired
	private EntrepriseService entrepriseService;

	@GetMapping("/retreiveAllEntreprises")
	public List<Entreprise> getEntreprise() {
		return entrepriseService.getEntreprise();
	}
	
	@PostMapping(value = "/addEntreprise", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) {
		return new ResponseEntity<>(entrepriseService.addEntreprise(entreprise), HttpStatus.OK);
	}

	@PutMapping(value = "/updateEntreprise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable(value = "id") int id,
    										@RequestBody Entreprise entreprise){
		return new ResponseEntity<>(entrepriseService.updateEntreprise(id, entreprise), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteEntreprise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEntreprise(@PathVariable(value = "id") int id){
		return new ResponseEntity<>(entrepriseService.deleteEntreprise(id), HttpStatus.OK);
	}
}
