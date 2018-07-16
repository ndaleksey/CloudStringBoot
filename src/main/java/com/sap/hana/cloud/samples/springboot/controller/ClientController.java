package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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

	@PostMapping(path = "/{id}/attach_checks", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus
	public ResponseEntity<Client> attacheChecksToClient(@RequestBody Long[] checks, BindingResult
			bindingResult, @PathVariable("id")Long id) {
		Client client = clientService.findById(id);
		client.getChecks().clear();

		for (Long checkId : checks) {
			Check check = checkService.findById(checkId);

			if (check != null)
				client.getChecks().add(check);
		}
		return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
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

	@GetMapping("/{id}/checks")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Long[] getChecksByClientId(@PathVariable("id") Long id) {
		List<Long> list = clientService.findById(id).getChecks().stream().map(c -> c.getId()).collect(Collectors.toList());
		return list.toArray(new Long[list.size()]);
	}

	/*@DeleteMapping(path = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		clientService.delete(id);
		return "redirect:/clients";
	}*/
}
