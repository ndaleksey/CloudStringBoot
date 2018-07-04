package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Controller
@RequestMapping(path = "/checks")
public class CheckController {
	private CheckService service;

	public CheckController(CheckService service) {
		this.service = service;
	}

	@GetMapping
	public String showChecks(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("checks", service.findByPage(page, 10));
		return "checks";
	}

	@PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Check addCheck(@RequestBody Check check, BindingResult result) {
		service.save(check);
		return check;
	}

	@GetMapping(path = "/delete")
	public String deleteCheck(Long id) {
		service.delete(id);
		return "redirect:/checks";
	}

	@GetMapping(path = "/find")
	@ResponseBody
	public Check find(Long id) {
		Check result = service.findById(id);
		return result;
	}
}
