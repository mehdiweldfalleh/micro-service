package com.esprit.microservice.clientservice.Controller;

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

import com.esprit.microservice.clientservice.Entity.Client;
import com.esprit.microservice.clientservice.Service.ClientService;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@PostMapping(value = "/addClient", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		return new ResponseEntity<>(clientService.addClient(client), HttpStatus.OK);
	}
	@PutMapping(value = "/updateClient/{cin}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Client> updateClient(@PathVariable(value = "cin") int cin,
    										@RequestBody Client client){
		return new ResponseEntity<>(clientService.updateClient(cin, client), HttpStatus.OK);
	}
	@DeleteMapping(value = "/deleteClient/{cin}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteClient(@PathVariable(value = "cin") int cin){
		return new ResponseEntity<>(clientService.deleteClient(cin), HttpStatus.OK);
	}
	
	@GetMapping(value="/retreiveAllClients")
	public List<Client> retreiveAllClients(){
		return clientService.retreiveAllClients();
	}
	
}
