package com.chrisenochdatingsite.Dating.site.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.AnswerWeightService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptionsService;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerMultiService;
import com.chrisenochdatingsite.Dating.site.service.UserService;
import com.chrisenochdatingsite.Dating.site.service.UtilService;

@Controller
public class HomeController {
	
	CategoryService categoryService;
	UserService userService;
	AnswerWeightService aWService;
	QuestionWithOptionsService qOpsService;
	SubmittedAnswerMultiService sAMSService;
	UtilService utilService;
	
	@Autowired
	public HomeController(CategoryService categoryService, UserService userService
			, AnswerWeightService aWService, QuestionWithOptionsService qOpsService
			, SubmittedAnswerMultiService sAMS, UtilService utilService) {
		this.categoryService = categoryService;
		this.userService = userService;
		this.aWService = aWService;
		this.qOpsService = qOpsService;
		this.sAMSService = sAMS;
		this.utilService = utilService;
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
	
	@GetMapping("/adduser")
	public String addUser() {
		System.out.println("Inside add user");
		
		User user = new User("Chris", "Enoch", "chris@yahoo.com", LocalDate.now(), Sex.MALE);

		userService.save(user);
		
		//List<User> users = userService.findAll();
		//System.out.println(users);

		return "index";
	}
	
	@GetMapping("/addaw")
	public String addAnswerWeighted() {
		System.out.println("Inside add answerweighted");
		
		AnswerWeightedImpl aW = new AnswerWeightedImpl("Basketball", AnswerWeight.FOUR);

		aWService.save(aW);
		
		List<Answer> answers = aWService.findAll();
		System.out.println(answers);

		return "index";
	}
	
	@GetMapping("/addqops")
	public String addqops() {
		System.out.println("Inside add answerweighted");
		
		AnswerWeightedImpl aW1 = new AnswerWeightedImpl("Basketball");
		AnswerWeightedImpl aW2 = new AnswerWeightedImpl("Football");
		AnswerWeightedImpl aW3 = new AnswerWeightedImpl("Swimming");
		
		aWService.save(aW1);
		aWService.save(aW2);
		aWService.save(aW3);
		
//		List<Category> categories = categoryService.findAll();
//		
//		
//		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How mich do you like these sports?"
//				, categories.get(0), Arrays.asList(aW1, aW2, aW3));
		
		Category category = utilService.getReference(Category.class, 1);
		
		
		QuestionWithOptionsImpl qops = new QuestionWithOptionsImpl("How mich do you like these sports?"
				, category, Arrays.asList(aW1, aW2, aW3));

		
		qOpsService.save(qops);
		
		List<Question> qs = qOpsService.findAll();
		System.out.println(qs);

		return "index";
	}
	
	@GetMapping("/addsams")
	public String addsams() {
		System.out.println("Inside add answerweighted");
		
		List<User> users = userService.findAll();
		User user = users.get(0);
		
		List<Question> qs = qOpsService.findAll();
		
		AnswerWeightedImpl aW1 = new AnswerWeightedImpl("Basketball", AnswerWeight.FIVE);
		AnswerWeightedImpl aW2 = new AnswerWeightedImpl("Football", AnswerWeight.FOUR);
		AnswerWeightedImpl aW3 = new AnswerWeightedImpl("Swimming", AnswerWeight.SIX);
		
		aWService.save(aW1);
		aWService.save(aW2);
		aWService.save(aW3);
		
		var sAMS = new SubmittedAnswerMultiImpl(qs.get(0), user, Arrays.asList(aW1, aW2, aW3));
		sAMSService.save(sAMS);


		return "index";
	}

}
