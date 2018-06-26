package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@RestController
@RequestMapping(path = "/clients")
public class ClientRestController {
	private ClientService service;

	public ClientRestController(ClientService service) {
		this.service = service;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		service.saveClient(client);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@DeleteMapping(path = "/del")
	public ResponseEntity<Client> deleteById(@RequestBody Client client) {
		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	public void deleteMultiple(@RequestParam(value = "id", required = true) Long[] ids) {
		service.deleteClientByIds(ids);
	}

	/*@DeleteMapping(path = "/delete")
	public ResponseEntity<Client[]> delete(@RequestBody Client[] clients){
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}*/
}
