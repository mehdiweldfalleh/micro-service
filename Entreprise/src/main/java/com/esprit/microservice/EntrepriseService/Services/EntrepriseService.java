package com.esprit.microservice.EntrepriseService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.microservice.EntrepriseService.Entity.Entreprise;
import com.esprit.microservice.EntrepriseService.Repositories.EntrepriseRepository;



@Service
public class EntrepriseService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	public Entreprise addEntreprise(Entreprise entrepise) {
		return entrepriseRepository.save(entrepise);
	}
	public Entreprise updateEntreprise(int id, Entreprise newEntreprise) {
		if (entrepriseRepository.findById(id).isPresent()) {
			Entreprise oldEntreprise = entrepriseRepository.findById(id).get();
			oldEntreprise.setNom(newEntreprise.getNom());
			oldEntreprise.setEmail(newEntreprise.getEmail());
			
			return entrepriseRepository.save(oldEntreprise);
		} else
			return null;
	}
	public String deleteEntreprise(int id) {
		if (entrepriseRepository.findById(id).isPresent()) {
			entrepriseRepository.deleteById(id);
			return "Entreprise deleted";
		} else
			return "Entreprise does not exist";
	}

	public List<Entreprise> getEntreprise() {
		return entrepriseRepository.findAll();
	}
}
