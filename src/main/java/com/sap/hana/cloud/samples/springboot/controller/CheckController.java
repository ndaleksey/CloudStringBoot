package com.sap.hana.cloud.samples.springboot.controller;

import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.service.CheckPositionService;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
	public ModelAndView showChecks(ModelMap model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("checks", checkService.findByPage(page, 10));
		return new ModelAndView("checks", model);
	}

	@GetMapping("/{id}/positions")
	public ModelAndView showCheckPositions(@PathVariable("id") Long id, ModelMap model) {
		Check check = checkService.findById(id);

		if (check == null) return new ModelAndView("error");

		List<CheckPosition> positions = check.getPositions().stream().sorted(Comparator.comparing(p -> p.getNumber())
		).collect(Collectors.toList());

		model.addAttribute("check", check);
		model.addAttribute("positions", positions);
		return new ModelAndView("check_positions", model);
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

	@GetMapping("/{id}")
	@ResponseBody
	public Check findCheckById(@PathVariable("id") Long id) {
		return checkService.findById(id);
	}

	@GetMapping("/{id}/details")
	public String showCheckDetails(@PathVariable("id") Long id, ModelMap model) {
		Check check = checkService.findById(id);
		model.addAttribute("check", check);

		return "check";
	}

	@GetMapping("/positions/{posId}")
	@ResponseBody
	public CheckPosition findPositionById(@PathVariable("posId") Long posId) {
		return positionService.findById(posId);
	}

	@GetMapping("/{checkId}/positions/{posId}/details")
	public ModelAndView showPositionDetails(@PathVariable("checkId") Long checkId, @PathVariable("posId") Long posId,
											ModelMap model) {
		CheckPosition position = positionService.findById(posId);
		model.addAttribute("position", position);

		return new ModelAndView("position_details", model);
	}

	@PostMapping(value = "/{checkId}/positions/{posId}/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String save(@PathVariable("checkId") Long checkId, @PathVariable("posId") Long posId, @Valid
	@ModelAttribute("position") CheckPosition position, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "error";

		CheckPosition oldPosition = positionService.findById(posId);
		oldPosition.setAmount(position.getAmount());
		oldPosition.setNumber(position.getNumber());
		oldPosition.setPrice(position.getPrice());
		positionService.save(oldPosition);

		return "redirect:/checks/" + checkId + "/positions";
	}

	@DeleteMapping(value = "/positions", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public CheckPosition deletePosition(@RequestBody CheckPosition position) {
		position = positionService.findById(position.getId());
		positionService.delete(Long.valueOf(position.getId()));
		return position;
	}
}