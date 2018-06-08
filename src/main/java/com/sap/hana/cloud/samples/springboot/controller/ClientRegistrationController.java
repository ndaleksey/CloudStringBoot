package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@RestController
@RequestMapping(path = "/clients")
public class ClientRegistrationController {
	private ClientService service;

	public ClientRegistrationController(ClientService service) {
		this.service = service;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		service.saveClient(client);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
