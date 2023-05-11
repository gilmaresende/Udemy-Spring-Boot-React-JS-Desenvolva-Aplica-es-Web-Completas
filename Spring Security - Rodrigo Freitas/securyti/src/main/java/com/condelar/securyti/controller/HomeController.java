package com.condelar.securyti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class HomeController {


	@GetMapping("/home")
	public String home() {
		return "LIVRE";
	}
	
	@GetMapping("/home1")
	public String home1() {
		return "USER";
	}
	
	@GetMapping("/home2")
	public String home2() {
		return "ADMIN";
	}
	
	@GetMapping("/homev")
	public String home3() {
		return "VISITANTE";
	}
}
