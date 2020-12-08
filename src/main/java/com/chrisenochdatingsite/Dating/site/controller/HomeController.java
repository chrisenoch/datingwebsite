package com.chrisenochdatingsite.Dating.site.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.AnswerWeightedService;
import com.chrisenochdatingsite.Dating.site.service.BatchUpdateService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptionsService;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerMultiService;
import com.chrisenochdatingsite.Dating.site.service.UserService;
import com.chrisenochdatingsite.Dating.site.service.UtilService;

@Controller
public class HomeController {
	
	CategoryService categoryService;
	UserService userService;
	AnswerWeightedService answerWeightedService;
	QuestionWithOptionsService questionWithOptionsService;
	SubmittedAnswerMultiService submittedAnswerMultiService;
	UtilService utilService;
	BatchUpdateService batchUpdateService;
	
	@Autowired
	public HomeController(CategoryService categoryService, UserService userService
			, AnswerWeightedService answerWeightedService, QuestionWithOptionsService questionWithOptionsService
			, SubmittedAnswerMultiService submittedAnswersMultiService, UtilService utilService
			, BatchUpdateService batchUpdateService) {
		this.categoryService = categoryService;
		this.userService = userService;
		this.answerWeightedService = answerWeightedService;
		this.questionWithOptionsService = questionWithOptionsService;
		this.submittedAnswerMultiService = submittedAnswersMultiService;
		this.utilService = utilService;
		this.batchUpdateService = batchUpdateService;
	}
	
	@GetMapping("/batchupdate")
	public String batchUpdateMembershipType() {
		System.out.println("Inside batchUpdateMembershipType");
		
		batchUpdateService.batchUpdateMembershipType();
		
		return "index";
	}
	
	@GetMapping("/addcategory")
	public String addCategory() {
		System.out.println("Inside addCategory");
		
		Category category = new Category("Movies");
		categoryService.save(category);
		
		List<Category> categories = categoryService.findAll();
		System.out.println(categories);

		return "index";
	}
	
	@GetMapping("/adduser")
	public String addUser() {
		System.out.println("Inside addUser");
		
		User user = new User("James", "Enoch", "chris@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
		User user2 = new User("Pete", "Enoch", "pete@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
		User user3 = new User("Sarah", "Enoch", "sarah@yahoo.com", LocalDate.now(), Sex.FEMALE, MembershipType.TRIAL);
		User user4 = new User("Molly", "Enoch", "molly@yahoo.com", LocalDate.now(), Sex.FEMALE, MembershipType.TRIAL);
		User user5 = new User("TJ", "Enoch", "tj@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
		User user6 = new User("Lee", "Enoch", "lee@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
		User user7 = new User("Jane", "Enoch", "jane@yahoo.com", LocalDate.now(), Sex.FEMALE, MembershipType.TRIAL);

		userService.save(user);
		userService.save(user2);
		userService.save(user3);
		userService.save(user4);
		userService.save(user5);
		userService.save(user6);
		userService.save(user7);
		

		return "index";
	}
	
	@GetMapping("/addanswerweighted")
	public String addAnswerWeighted() {
		System.out.println("Inside addAnswerweighted");
		
		AnswerWeightedImpl answerWeightedImpl = new AnswerWeightedImpl("Basketball", AnswerWeight.TWO);
		AnswerWeightedImpl answerWeightedImpl2 = new AnswerWeightedImpl("Football", AnswerWeight.THREE);
		AnswerWeightedImpl answerWeightedImpl3 = new AnswerWeightedImpl("Swimming", AnswerWeight.SIX);

		answerWeightedService.save(answerWeightedImpl );
		answerWeightedService.save(answerWeightedImpl2 );
		answerWeightedService.save(answerWeightedImpl3 );
		
		System.out.println("AnswerWeight int value: " + answerWeightedImpl.getAnswerWeight().getWeight());
		System.out.println("mod test: " + 0%7);
		
		List<Answer> answers = answerWeightedService.findAll();
		System.out.println(answers);

		return "index";
	}
	
	@GetMapping("/addqopswithrefs")
	public String addQOpsWithrefs() {
		System.out.println("Inside add answerweighted");
		
		Answer aW1 =  utilService.getReference(Answer.class, 51L);
		Answer aW2 =  utilService.getReference(Answer.class, 52L);
		Answer aW3 =  utilService.getReference(Answer.class, 53L);
		
		Category category = utilService.getReference(Category.class, 42);
		
		
		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How much do you like these sports?"
				, category, Arrays.asList(aW1, aW2, aW3));

		questionWithOptionsService.save(qops);
		
		List<Question> qs = questionWithOptionsService.findAll();
		System.out.println(qs);

		return "index";
	}
	
	@GetMapping("/addqops")
	public String addQOps() {
		System.out.println("Inside add answerweighted");

		Category category = utilService.getReference(Category.class, 1);

		AnswerWeightedImpl aWHockey =  new AnswerWeightedImpl("Hockey");
		AnswerWeightedImpl aWWaterpolo =  new AnswerWeightedImpl("Waterpolo");
		AnswerWeightedImpl aWGymnastics =  new AnswerWeightedImpl("Gymnastics");
		
		List<Answer> answers = new ArrayList<>();
		answers.add(aWHockey);
		answers.add(aWWaterpolo);
		answers.add(aWGymnastics);
		
		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How much do you like these sports?"
				, category, answers);
		

		questionWithOptionsService.save(qops);
		
		List<Question> questions = questionWithOptionsService.findAll();
		System.out.println(questions);

		return "index";
	}
	
	@GetMapping("/checkforeach") //Add as a test method
	public String checkforeach() {
		System.out.println("Inside add answerweighted");

		Category category = utilService.getReference(Category.class, 1);
		
		AnswerWeightedImpl aWHockey =  new AnswerWeightedImpl("Hockey");
		AnswerWeightedImpl aWWaterpolo =  new AnswerWeightedImpl("Waterpolo");
		AnswerWeightedImpl aWGymnastics =  new AnswerWeightedImpl("Gymnastics");
		
		List<Answer> answers = new ArrayList<>();
		answers.add(aWHockey);
		answers.add(aWWaterpolo);
		answers.add(aWGymnastics);

		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How much do you like these sports?"
				, category, answers);
		
		System.out.println("before");
		qops.getPossibleAnswers().forEach(System.out::println);
		System.out.println("after");
		
		questionWithOptionsService.save(qops);
		
		List<Question> qs = questionWithOptionsService.findAll();
		System.out.println(qs);

		return "index";
	}
	
	@GetMapping("/addsubmultiimpl")
	public String addsubmittedAnswersMultiService() {
		System.out.println("Inside add answerweighted");
		
		List<User> users = userService.findAll();
		User user = users.get(0);
		
		Question q = utilService.getReference(Question.class, 56);
		
		AnswerWeightedImpl aWBasketball = new AnswerWeightedImpl("Basketball", AnswerWeight.FIVE);
		AnswerWeightedImpl aWFootball = new AnswerWeightedImpl("Football", AnswerWeight.FOUR);
		AnswerWeightedImpl aWSwimming = new AnswerWeightedImpl("Swimming", AnswerWeight.SIX);
		
		answerWeightedService.save(aWBasketball);
		answerWeightedService.save(aWFootball);
		answerWeightedService.save(aWSwimming);
		
		var submittedAnswerMultiImpl = new SubmittedAnswerMultiImpl(q, user, Arrays.asList(aWBasketball
				, aWFootball, aWSwimming));
		submittedAnswerMultiService.save(submittedAnswerMultiImpl);


		return "index";
	}

}
