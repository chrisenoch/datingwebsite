package com.chrisenochdatingsite.Dating.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		System.out.println("Hello");
		return "index";
	}
	

}
