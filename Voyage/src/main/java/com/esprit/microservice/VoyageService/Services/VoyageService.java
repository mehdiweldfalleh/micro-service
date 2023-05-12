package com.esprit.microservice.VoyageService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.microservice.VoyageService.Entity.Voyage;
import com.esprit.microservice.VoyageService.Repositories.VoyageRepository;

@Service
public class VoyageService {

	@Autowired
	private VoyageRepository voyageRepository;

	public Voyage addVoyage(Voyage voyage) {
		return voyageRepository.save(voyage);
	}
	public Voyage updateVoyage(int id, Voyage newVoyage) {
		if (voyageRepository.findById(id).isPresent()) {
			Voyage oldVoyage = voyageRepository.findById(id).get();
			oldVoyage.setReference(newVoyage.getReference());
			oldVoyage.setPrice(newVoyage.getPrice());
			oldVoyage.setDescription(newVoyage.getDescription());
			oldVoyage.setFlight(newVoyage.getFlight());
			oldVoyage.setClientId(newVoyage.getClientId());

			return voyageRepository.save(oldVoyage);
		} else
			return null;
	}
	public String deleteVoyage(int id) {
		if (voyageRepository.findById(id).isPresent()) {
			voyageRepository.deleteById(id);
			return "Voyage deleted";
		} else
			return "Voyage does not exist";
	}

	public List<Voyage> retreiveAllVoyages() {
		return voyageRepository.findAll();
	}
}
