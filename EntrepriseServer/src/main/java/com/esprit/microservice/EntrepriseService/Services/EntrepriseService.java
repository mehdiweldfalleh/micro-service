package com.esprit.microservice.EntrepriseService.Services;

import com.esprit.microservice.EntrepriseService.Repositories.EntrepriseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.microservice.EntrepriseService.Entity.Entreprise;

import java.util.List;
@Service("EntrepriseServices")
public class EntrepriseService{
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public Entreprise addEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
    public Entreprise updateEntreprise(int id, Entreprise newentreprise) {
        if (entrepriseRepository.findById(id).isPresent()) {
            Entreprise oldEntreprise = entrepriseRepository.findById(id).get();
            oldEntreprise.setNom(newentreprise.getNom());
            oldEntreprise.setEmail(newentreprise.getEmail());
            return entrepriseRepository.save(oldEntreprise);
        } else
            return null;
    }
    public String deleteEntreprise(int cin) {
        if (entrepriseRepository.findById(cin).isPresent()) {
            entrepriseRepository.deleteById(cin);
            return "Entreprise deleted";
        } else
            return "Entreprise does not exist";
    }

    public List<Entreprise> retreiveAllEntreprises(){
        return entrepriseRepository.findAll();
    }

}
