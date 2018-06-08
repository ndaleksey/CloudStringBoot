package com.sap.hana.cloud.samples.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shishkov A.V. on 07.06.18.
 */
@Controller
@RequestMapping(path = {"/", "/index"})
public class WelcomeController {

	@GetMapping(path = "/")
	public String welcome() {
		return "clients";
	}


}