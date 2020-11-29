package com.chrisenochdatingsite.Dating.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;

@Controller
public class HomeController {
	
	CategoryService categoryService;
	
	@Autowired
	public HomeController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/home")
	public String home() {
		System.out.println("Hello");
		
		Category category = new Category("Lifestyle");
		categoryService.save(category);
		
		List<Category> categories = categoryService.findAll();
		System.out.println(categories);

		return "index";
	}
	

}
