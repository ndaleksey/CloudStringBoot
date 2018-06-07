package com.sap.hana.cloud.samples.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shishkov A.V. on 07.06.18.
 */
@RestController
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "Hello again!";
	}
}