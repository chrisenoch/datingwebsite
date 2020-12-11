package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Arrays;
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
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.AnswerService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptionsService;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerMultiService;
import com.chrisenochdatingsite.Dating.site.service.UtilService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestH2 {

	
	//AnswerWeightedService answerWeightedService;
	CategoryService categoryService;
	AnswerService answerService;
	QuestionWithOptionsService questionWithOptionsService;
	UtilService utilService;
	SubmittedAnswerMultiService submittedAnswerMultiService; 
	
	@Autowired
	public TestH2(CategoryService categoryService,  AnswerService answerService
			, QuestionWithOptionsService questionWithOptionsService, UtilService utilService
			, SubmittedAnswerMultiService submittedAnswerMultiService) {
		this.categoryService = categoryService;
		this.answerService = answerService;
		this.questionWithOptionsService = questionWithOptionsService;
		this.utilService = utilService;
		this.submittedAnswerMultiService =submittedAnswerMultiService;
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
	
	
	@Test
	@Order(1) 
	//Java 8
	public void getAnswerImpl() throws Exception {
		List<Answer> answers = answerService.findAll();
		
//		Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerImpl> answersImplSortedByIdAsc = answers.stream().sorted(Comparator.comparing(Answer::getId)
				).filter(object-> object instanceof AnswerImpl).map(a-> (AnswerImpl) a).collect(Collectors.toList());
		
		AnswerImpl answersWeightedWeightNotSet = answersImplSortedByIdAsc.get(0);
		
		//Question should be set and answer weight should NOT be set as the answer is a possible answer to a question
		assertEquals(10, answersWeightedWeightNotSet.getId());
		assertEquals(13, answersWeightedWeightNotSet.getQuestion().getId());
		assertEquals(3, answersImplSortedByIdAsc.size());
		
	}
	
	@Test
	@Order(2) 
	public void saveAnswerImpl() {
		AnswerImpl ansImpl = new AnswerImpl("Polo");
		
		answerService.save(ansImpl);
		List<Answer> answers = answerService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so sort list by id
		List<AnswerImpl> answersImplSortedByIdAsc = answers.stream().sorted(Comparator.comparing(Answer::getId)
				).filter(o-> o instanceof AnswerImpl).map(a-> (AnswerImpl) a).collect(Collectors.toList());
		

		assertEquals("Polo",answersImplSortedByIdAsc.get(3).getAnswerText());
		assertNull(answersImplSortedByIdAsc.get(3).getQuestion());
		assertEquals(4, answersImplSortedByIdAsc.size());
		
		System.out.println("answersimpl id" + answersImplSortedByIdAsc.get(3).getId());
		
	}
	
	
	@Test
	@Order(1) 
	//Java 8
	public void getQuestionWithoptionsImpl() throws Exception {
		List<Question> questions = questionWithOptionsService.findAll();
		
//		Order of retrieval from MySQL not guarenteed so sort list by id
		List<QuestionWithOptionsImpl> questionWithOptionsImplSortedByIdAsc = questions.stream().sorted(Comparator.comparing(Question::getId)
				).filter(object-> object instanceof QuestionWithOptionsImpl).map(a-> (QuestionWithOptionsImpl) a).collect(Collectors.toList());
		
		QuestionWithOptionsImpl questionWithOptionsImpl = questionWithOptionsImplSortedByIdAsc.get(0);

		assertEquals(13, questionWithOptionsImpl.getId());
		assertEquals("How much do you like these sports?", questionWithOptionsImpl.getQuestionText());
		assertEquals(1, questionWithOptionsImplSortedByIdAsc.size());
		
	}
	
	@Test
	@Order(2) 
	public void saveQuestionWithOptionsImpl() {
		
		//Arrange
		Answer aW1 =  new AnswerImpl("Horror");
		Answer aW2 =  new AnswerImpl("Thriller");
		Answer aW3 =  new AnswerImpl("Comedy");
		
		answerService.save(aW1);
		answerService.save(aW2);
		answerService.save(aW3);
		
		List<Answer> possibleAnswers = Arrays.asList(aW1, aW2, aW3);

		Category category = new Category("Movies");
		categoryService.save(category);
		
		QuestionWithOptionsImpl questionWithOptionsImpl = new QuestionWithOptionsImpl("How much do you like the following genres?"
				, category, Arrays.asList(aW1));
			 
		
		//Act
		questionWithOptionsService.save(questionWithOptionsImpl);
		List<Question> questions = questionWithOptionsService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so sort list by id
		List<QuestionWithOptionsImpl> questionWithOptionsImplSortedByIdAsc = questions.stream().sorted(Comparator.comparing(Question::getId)
				).filter(object-> object instanceof QuestionWithOptionsImpl).map(a-> (QuestionWithOptionsImpl) a).collect(Collectors.toList());
		
		
		//Assert
		assertEquals("How much do you like the following genres?", questionWithOptionsImpl.getQuestionText());
		assertEquals(2, questionWithOptionsImplSortedByIdAsc.size());
			
	}
	
	@Test
	@Order(1) 
	//Java 8
	public void getSubmittedAnswerMultiImpl() throws Exception {
		List<SubmittedAnswerMultiImpl> submittedAnswerMultiImpls = submittedAnswerMultiService.findAll();
		
//		Order of retrieval from MySQL not guarenteed so sort list by id
		List<SubmittedAnswerMultiImpl> submittedAnswerMultiImplsSortedByIdAsc = submittedAnswerMultiImpls .stream().sorted(Comparator.comparing(SubmittedAnswerMultiImpl ::getId)
				).filter(object-> object instanceof SubmittedAnswerMultiImpl).map(a-> (SubmittedAnswerMultiImpl) a).collect(Collectors.toList());
		
		SubmittedAnswerMultiImpl submittedAnswerMultiImpl = submittedAnswerMultiImplsSortedByIdAsc.get(0);

		assertEquals(17, submittedAnswerMultiImpl.getId());
		assertEquals(13, submittedAnswerMultiImpl.getQuestion().getId());
		assertEquals(2, submittedAnswerMultiImpl.getUser().getId());
		assertEquals(1, submittedAnswerMultiImplsSortedByIdAsc.size());
		
	}
	
	@Test
	@Order(2) 
	public void saveSubmittedAnswerMultiServiceImpl() {
		
		//Arrange
		Answer aW1 =  utilService.getReference(Answer.class, 10L);
		Answer aW2 =  utilService.getReference(Answer.class, 11L);
		Answer aW3 =  utilService.getReference(Answer.class, 12L);

		List<Answer> selectedAnswers = Arrays.asList(aW1, aW2, aW3);
		Category category = utilService.getReference(Category.class, 1);
		Question questionWithOptionsImpl = utilService.getReference(Question.class, 13);
		User user = utilService.getReference(User.class, 4L);
			
		SubmittedAnswerMultiImpl submittedAnswerMultiImpl = new SubmittedAnswerMultiImpl(questionWithOptionsImpl, user, selectedAnswers);
		
		//Act
		submittedAnswerMultiService.save( submittedAnswerMultiImpl);	
		List<SubmittedAnswerMultiImpl> submittedAnswerMultiImpls = submittedAnswerMultiService.findAll();
		
		//Order of retrieval from MySQL not guarenteed so sort list by id
		List<SubmittedAnswerMultiImpl> submittedAnswerMultiImplSortedByIdAsc = submittedAnswerMultiImpls.stream().sorted(Comparator.comparing(SubmittedAnswerMultiImpl::getId)
				).filter(object-> object instanceof SubmittedAnswerMultiImpl).map(a-> (SubmittedAnswerMultiImpl) a).collect(Collectors.toList());
		
//		
		//Assert
		assertEquals(13, submittedAnswerMultiImpl.getQuestion().getId());
		assertEquals(4, submittedAnswerMultiImpl.getUser().getId());
		assertEquals(2, submittedAnswerMultiImplSortedByIdAsc.size());
			
	}
	
	
		
}
