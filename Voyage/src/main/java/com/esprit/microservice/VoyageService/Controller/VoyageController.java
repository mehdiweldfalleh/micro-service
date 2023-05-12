package com.esprit.microservice.VoyageService.Controller;

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

import com.esprit.microservice.VoyageService.Entity.Voyage;
import com.esprit.microservice.VoyageService.Services.VoyageService;

@RestController
@RequestMapping(value = "/voyages")
public class VoyageController {

	@Autowired
	private VoyageService voyageService;

	@GetMapping("/retreiveAllVoyages")
	public List<Voyage> retreiveAllVoyages() {
		return voyageService.retreiveAllVoyages();
	}
	
	@PostMapping(value = "/addVoyage", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Voyage> addVoyage(@RequestBody Voyage voyage) {
		return new ResponseEntity<>(voyageService.addVoyage(voyage), HttpStatus.OK);
	}

	@PutMapping(value = "/updateVoyage/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Voyage> updateVoyage(@PathVariable(value = "id") int id,
    										@RequestBody Voyage voyage){
		return new ResponseEntity<>(voyageService.updateVoyage(id, voyage), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteVoyage/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteVoyage(@PathVariable(value = "id") int id){
		return new ResponseEntity<>(voyageService.deleteVoyage(id), HttpStatus.OK);
	}
}
