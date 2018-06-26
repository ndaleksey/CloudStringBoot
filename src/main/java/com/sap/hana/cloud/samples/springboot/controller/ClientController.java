package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Controller
@RequestMapping(path = "/clients")
public class ClientController {
	private ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	@GetMapping(path = "/all")
	public String showAllClients(Model model) {
		model.addAttribute("clients", service.getAllClients());
		return "clients";
	}

	@GetMapping(path = "/list")
	public ResponseEntity<List<Client>> getAllClients() {
		return new ResponseEntity<>(service.getAllClients(), HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		service.saveClient(client);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
