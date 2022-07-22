package com.angel.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.angel.entity.Client;
import com.angel.service.IClientService;

@Controller
@RequestMapping("user")
public class ClientController {
	@Autowired
	private IClientService clientService;

	@RequestMapping(value="client/{id}", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		Client client = clientService.getClientById(id);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value="clients", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> list = clientService.getAllClients();
		return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
	}

	@RequestMapping(value="addClient", method= RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addClient(@RequestBody Client client, UriComponentsBuilder builder) {
        boolean flag = clientService.addClient(client);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getClientId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="addVariousClient", method= RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> addVariousClient(@RequestBody List<Client> clients, UriComponentsBuilder builder) {
		boolean flag = clientService.addVariousClient(clients);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/clients").build().toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="updateClient", method= RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		clientService.updateClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value="deleteClient/{id}", method= RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 