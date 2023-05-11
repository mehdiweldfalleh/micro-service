package com.esprit.microservice.clientservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.microservice.clientservice.Entity.Client;
import com.esprit.microservice.clientservice.Repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client addClient(Client client) {
		return clientRepository.save(client);
	}
	public Client updateClient(int cin, Client newClient) {
		if (clientRepository.findById(cin).isPresent()) {
			Client oldClient = clientRepository.findById(cin).get();
			oldClient.setFirstName(newClient.getFirstName());
			oldClient.setSecondName(newClient.getSecondName());
			oldClient.setEmail(newClient.getEmail());
			oldClient.setAddress(newClient.getAddress());
			oldClient.setPhoneNumber(newClient.getPhoneNumber());

			return clientRepository.save(oldClient);
		} else
			return null;
	}
	public String deleteClient(int cin) {
		if (clientRepository.findById(cin).isPresent()) {
			clientRepository.deleteById(cin);
			return "client deleted";
		} else
			return "client does not exist";
	}
	
	public List<Client> retreiveAllClients(){
		return clientRepository.findAll();
	}
}
