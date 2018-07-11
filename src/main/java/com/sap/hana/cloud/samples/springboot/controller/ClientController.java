package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Controller
@RequestMapping(path = "/clients")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@Autowired
	private CheckService checkService;

	@GetMapping
	public String showClients(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("clients", clientService.findByPage(page, 10));
		model.addAttribute("checks", checkService.findAll());
		return "clients";
	}

	@PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public Client addClient(@RequestBody Client client, BindingResult result) {
		clientService.save(client);
		return client;
	}

	@GetMapping(path = "/delete")
	public String deleteClient(Long id) {
		clientService.delete(id);
		return "redirect:/clients";
	}

	@GetMapping(path = "/find")
	@ResponseBody
	public Client find(Long id) {
		return clientService.findById(id);
	}

	/*@DeleteMapping(path = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		clientService.delete(id);
		return "redirect:/clients";
	}*/
}
