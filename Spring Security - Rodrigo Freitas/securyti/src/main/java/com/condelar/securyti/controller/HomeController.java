package com.condelar.securyti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/home1")
	public String home1() {
		return "home1";
	}
	
	@GetMapping("/home2")
	public String home2() {
		return "home2";
	}
}
