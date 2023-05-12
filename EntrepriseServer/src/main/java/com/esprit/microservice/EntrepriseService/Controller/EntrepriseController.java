package com.esprit.microservice.EntrepriseService.Controller;

import com.esprit.microservice.EntrepriseService.Services.EntrepriseService;
import com.esprit.microservice.EntrepriseService.Entity.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.MediaType;
@RestController
@RequestMapping(value = "/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseService entrepriseService;

    @PostMapping(value = "/addEntreprise", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) {
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

    @GetMapping(value="/retreiveAllEntreprises")
    public List<Entreprise> retreiveAllEntreprises(){
        return entrepriseService.retreiveAllEntreprises();
    }
}