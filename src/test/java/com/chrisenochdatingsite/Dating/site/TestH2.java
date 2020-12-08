package com.chrisenochdatingsite.Dating.site;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TestH2 {

	@Autowired
	//AnswerWeightedService answerWeightedService;
	CategoryService categoryService;
	
//	@Test
//	public void getAnswers() {
//		List<Answer> answers = answerWeightedService.findAll();
//		answers.forEach(System.out::println);
//		
//	}
	
	@Test
	public void getCategorry() {
		List<Category> categories = categoryService.findAll();
		categories.forEach(System.out::println);
		
	}
	
	
	
	
	
}
