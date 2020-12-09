package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import com.chrisenochdatingsite.Dating.site.service.AnswerService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestH2 {

	
	//AnswerWeightedService answerWeightedService;
	CategoryService categoryService;
	AnswerService answerService;
	
	@Autowired
	public TestH2(CategoryService categoryService,  AnswerService answerService) {
		this.categoryService = categoryService;
		this.answerService = answerService;
	}
	
	@Test
	@Order(1) 
	public void getCategory() {
		List<Category> categories = categoryService.findAll();
		assertEquals("Movies", categories.get(0).getCategory());
		assertEquals(1, categories.get(0).getId());
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
		assertEquals(18, lifestyle.getId());
		assertEquals(2, categoriesMap.size());
	}
	
	@Test
	@Order(1) 
	//Java 8
	public void getAnswerWeighted() throws Exception {
		List<Answer> answers = answerService.findAll();
		
//		Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerWeightedImpl> answersWeightedSortedByIdAsc = answers.stream().sorted(Comparator.comparing(Answer::getId)
				).filter(object-> object instanceof AnswerWeightedImpl).map(a-> (AnswerWeightedImpl) a).collect(Collectors.toList());
		

		Answer answer1 = answersWeightedSortedByIdAsc.get(0);
	
		AnswerWeightedImpl answersWeightedWeightSet = (AnswerWeightedImpl)answer1;
		
		//Question should be set and answer weight should NOT be set as the answer is a possible answer to a question
//		assertEquals(10, answersWeightedWeightNotSet.getId());
//		assertEquals(13, answersWeightedWeightNotSet.getQuestion().getId());
//		assertNull(answersWeightedWeightNotSet.getAnswerWeight());
		
		//Question should NOT be set and answer weight should be set. Question information should be in SubmittedAnswerMultiImpl class
		assertEquals(14, answersWeightedWeightSet.getId());
		assertNull(answersWeightedWeightSet.getQuestion());
		assertEquals(5, answersWeightedWeightSet.getAnswerWeight().getWeight());
		
		
		assertEquals(3, answersWeightedSortedByIdAsc.size());
		
	}
	
	@Test
	@Order(2) 
	public void saveAnswerWeighted() {
		AnswerWeightedImpl ansWeighted = new AnswerWeightedImpl("Polo", AnswerWeight.TWO);
		
		answerService.save(ansWeighted);
		List<Answer> answers = answerService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerWeightedImpl> answersWeightedSortedByIdAsc = answers.stream().sorted(Comparator.comparing(Answer::getId)
				).filter(o-> o instanceof AnswerWeightedImpl).map(a-> (AnswerWeightedImpl) a).collect(Collectors.toList());
		

		assertEquals(2,answersWeightedSortedByIdAsc.get(3).getAnswerWeight().getWeight());
		assertEquals("Polo",answersWeightedSortedByIdAsc.get(3).getAnswerText());
		assertNull(answersWeightedSortedByIdAsc.get(3).getQuestion());
		assertEquals(4, answersWeightedSortedByIdAsc.size());
		
	}
	
	
	
	
	
}
