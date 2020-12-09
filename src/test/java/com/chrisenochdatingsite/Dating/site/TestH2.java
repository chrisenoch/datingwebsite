package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.service.AnswerWeightedService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestH2 {

	
	//AnswerWeightedService answerWeightedService;
	CategoryService categoryService;
	AnswerWeightedService answerWeightedService;
	
	@Autowired
	public TestH2(CategoryService categoryService,  AnswerWeightedService answerWeightedService) {
		this.categoryService = categoryService;
		this.answerWeightedService = answerWeightedService;
	}
	
	@Test
	@Order(1) 
	public void getCategory() {
		List<Category> categories = categoryService.findAll();
		assertEquals("Movies", categories.get(0).getCategory());
		assertEquals(42, categories.get(0).getId());
		assertEquals(1, categories.size());
	}
	
	@Test
	@Order(2) 
	public void saveCategory() {
		Category category = new Category("Lifestyle");
		categoryService.save(category);
		List<Category> categories = categoryService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so convert list to map.
		Map<String, Category> categoriesMap = categories.stream().collect(Collectors.toMap(a-> a.getCategory(), a-> a));

		//Map<String, Category> categoriesMap = categories.stream().map(a-> categoriesMap.put(a.getCategory(), a)).collect(Collectors.toMap());

		Category lifestyle = categoriesMap.get("Lifestyle");
		
		assertEquals("Lifestyle", lifestyle.getCategory());
		assertEquals(67, lifestyle.getId());
		assertEquals(2, categoriesMap.size());
	}
	
	@Test
	@Order(1) 
	//Java 8
	public void getAnswerWeighted() throws Exception {
		List<Answer> answers = answerWeightedService.findAll();
		
//		Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerWeightedImpl> answersSortedByIdAsc = answers.stream().sorted(Comparator.comparing(Answer::getId)
				).map(a-> (AnswerWeightedImpl) a).collect(Collectors.toList());
		
		System.out.println("Check sorted list by id asc prints properly");
		answersSortedByIdAsc.forEach(a-> System.out.println(a.getId()));
		
		Answer answer1 = answers.get(0);
		Answer answer2 = answers.get(3);
		AnswerWeightedImpl answersWeightedWeightNotSet = (AnswerWeightedImpl)answer1;
		AnswerWeightedImpl answersWeightedWeightSet = (AnswerWeightedImpl)answer2;
		
		//Question should be set and answer weight should NOT be set as the answer is a possible answer to a question
		assertEquals(51, answersWeightedWeightNotSet.getId());
		assertEquals(56, answersWeightedWeightNotSet.getQuestion().getId());
		assertNull(answersWeightedWeightNotSet.getAnswerWeight());
		
		//Question should NOT be set and answer weight should be set. Question information should be in SubmittedAnswerMultiImpl class
		assertEquals(63, answersWeightedWeightSet.getId());
		assertNull(answersWeightedWeightSet.getQuestion());
		assertEquals(5, answersWeightedWeightSet.getAnswerWeight().getWeight());
		
		
		assertEquals(6, answers.size());
		
	}
	
	@Test
	@Order(2) 
	public void saveAnswerWeighted() {
		AnswerWeightedImpl ansWeighted = new AnswerWeightedImpl("Polo", AnswerWeight.TWO);
		
		answerWeightedService.save(ansWeighted);
		List<Answer> answersWeighted = answerWeightedService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerWeightedImpl> answersWeightedSortedByIdAsc = answersWeighted.stream().sorted(Comparator.comparing(Answer::getId)
				).map(a-> (AnswerWeightedImpl) a).collect(Collectors.toList());
		

		assertEquals(2,answersWeightedSortedByIdAsc.get(6).getAnswerWeight().getWeight());
		assertEquals("Polo",answersWeightedSortedByIdAsc.get(6).getAnswerText());
		assertNull(answersWeightedSortedByIdAsc.get(6).getQuestion());
		assertEquals(7, answersWeighted.size());
		
	}
	
	
	
	
	
}
