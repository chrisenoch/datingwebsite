package com.chrisenochdatingsite.Dating.site.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	AnswerService answerService;
	QuestionWithOptionsService questionWithOptionsService;
	SubmittedAnswerMultiService submittedAnswerMultiService;
	UtilService utilService;
	BatchUpdateService batchUpdateService;

	
	@Autowired
	public HomeController(CategoryService categoryService, UserService userService
			,  QuestionWithOptionsService questionWithOptionsService
			, SubmittedAnswerMultiService submittedAnswersMultiService, UtilService utilService
			, BatchUpdateService batchUpdateService, AnswerService answerService) {
		this.categoryService = categoryService;
		this.userService = userService;
		this.questionWithOptionsService = questionWithOptionsService;
		this.submittedAnswerMultiService = submittedAnswersMultiService;
		this.utilService = utilService;
		this.batchUpdateService = batchUpdateService;
		this.answerService = answerService;
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
	
//	@GetMapping("/addusernomembertype")
//	public String addUserNoMemberType() {
//		System.out.println("Inside addUser");	
//		//User user = new User("James", "Enoch", "chris@yahoo.com", LocalDate.now(), Sex.MALE);
//
//		//userService.save(user);
//
//		return "index";
//	}
	
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

		answerService.save(answerWeightedImpl );
		answerService.save(answerWeightedImpl2 );
		answerService.save(answerWeightedImpl3 );
		
		System.out.println("AnswerWeight int value: " + answerWeightedImpl.getAnswerWeight().getWeight());
		System.out.println("mod test: " + 0%7);
		
		List<Answer> answers = answerService.findAll();
		System.out.println(answers);

		return "index";
	}
	
	@GetMapping("/addansimpl")
	public String addansimpl() {
		System.out.println("Inside addansimpl");
		
		AnswerImpl ans = new AnswerImpl("Basketball");
		AnswerImpl ans2 = new AnswerImpl("Football");
		AnswerImpl ans3 = new AnswerImpl("Swimming");
		answerService.save(ans);
		answerService.save(ans2);
		answerService.save(ans3);

		return "index";
	}
	
	@GetMapping("/addansweightedwrong")
	public String addansweightedwrong() {
		System.out.println("Inside addansweightedwrong");
		
		AnswerWeightedImpl ans = new AnswerWeightedImpl("Polo");
		answerService.save(ans);

		return "index";
	}
	
	@GetMapping("/addqopswithrefs")
	public String addQOpsWithrefs() {
		System.out.println("Inside addqopswithrefs");
		
		Answer aW1 =  utilService.getReference(Answer.class, 9L);
		Answer aW2 =  utilService.getReference(Answer.class, 10L);
		Answer aW3 =  utilService.getReference(Answer.class, 11L);
		
		Category category = utilService.getReference(Category.class, 1);
		
		
		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How much do you like these sports?"
				, category, Arrays.asList(aW1, aW2, aW3));

		questionWithOptionsService.save(qops);
		
		List<Question> qs = questionWithOptionsService.findAll();
		System.out.println(qs);

		return "index";
	}
	
	@GetMapping("/addqops")
	public String addQOps() {
		System.out.println("Inside addqops");

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
		System.out.println("Inside checkforeach");

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
		System.out.println("Inside addsubmittedAnswersMultiService");
		
		List<User> users = userService.findAll();
		User user = users.get(0);
		
		Question q = utilService.getReference(Question.class, 13);
		
		AnswerWeightedImpl aWBasketball = new AnswerWeightedImpl("Basketball", AnswerWeight.FIVE);
		AnswerWeightedImpl aWFootball = new AnswerWeightedImpl("Football", AnswerWeight.FOUR);
		AnswerWeightedImpl aWSwimming = new AnswerWeightedImpl("Swimming", AnswerWeight.SIX);
		
		answerService.save(aWBasketball);
		answerService.save(aWFootball);
		answerService.save(aWSwimming);
		
		var submittedAnswerMultiImpl = new SubmittedAnswerMultiImpl(q, user, new HashSet<>(Arrays.asList(aWBasketball
				, aWFootball, aWSwimming)));
		submittedAnswerMultiService.save(submittedAnswerMultiImpl);


		return "index";
	}

}
