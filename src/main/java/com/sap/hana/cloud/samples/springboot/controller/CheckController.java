package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.service.CheckPositionService;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Controller
@RequestMapping(path = "/checks")
public class CheckController {
	private CheckService checkService;
	private CheckPositionService positionService;

	public CheckController(CheckService service, CheckPositionService positionService) {
		this.checkService = service;
		this.positionService = positionService;
	}

	@GetMapping
	public String showChecks(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("checks", checkService.findByPage(page, 10));
		return "checks";
	}

	@GetMapping("/positions")
	public String showCheckPositions(Long id, Model model) {
		Check check = checkService.findById(id);

		if (check == null) return "Error";

		List<CheckPosition> positions = check.getPositions().stream().sorted(Comparator.comparing(p -> p.getNumber())
		).collect(Collectors.toList());
		model.addAttribute("check", check);
		model.addAttribute("positions", positions);
		return "check_positions";
	}

	@PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Check addCheck(@RequestBody Check check, BindingResult result) {
		checkService.save(check);
		return check;
	}

	@GetMapping(path = "/delete")
	public String deleteCheck(Long id) {
		checkService.delete(id);
		return "redirect:/checks";
	}

	@GetMapping(path = "/find")
	@ResponseBody
	public Check find(Long id) {
		Check result = checkService.findById(id);
		return result;
	}

	@GetMapping("/positions/details")
	public String showPositionDetails(Long id, Model model) {
		model.addAttribute("position", positionService.findById(id));
		return "position_details";
	}

	@PostMapping("/positions/save")
	@ResponseBody
	public CheckPosition save(@RequestBody CheckPosition position) {
		return positionService.save(position);
	}

	/*@PostMapping("/positions/delete")
	public String deletePosition(@RequestParam("checkId") Long checkId, @RequestParam("posId") Long posId) {
		positionService.delete(posId);
		return "redirect:/checks/positions/?id=" + checkId;
	}*/

	@DeleteMapping(value = "/positions/delete", consumes = {MediaType.TEXT_PLAIN_VALUE})
	public String deletePosition(@RequestBody String id) {
		positionService.delete(Long.valueOf(id));
		return "/checks/positions/delete";
	}
}
