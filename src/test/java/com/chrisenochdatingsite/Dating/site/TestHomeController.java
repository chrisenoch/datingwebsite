package com.chrisenochdatingsite.Dating.site;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.chrisenochdatingsite.Dating.site.service.AnswerService;
import com.chrisenochdatingsite.Dating.site.service.BatchUpdateService;
import com.chrisenochdatingsite.Dating.site.service.CategoryService;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptionsService;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerMultiService;
import com.chrisenochdatingsite.Dating.site.service.UserService;
import com.chrisenochdatingsite.Dating.site.service.UtilService;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 
import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


//@WebMvcTest(HomeController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestHomeController {

	    private MockMvc mockMvc;	 	
	 	CategoryService categoryService;
		UserService userService;
		AnswerService answerService;
		QuestionWithOptionsService questionWithOptionsService;
		SubmittedAnswerMultiService submittedAnswerMultiService;
		UtilService utilService;
		BatchUpdateService batchUpdateService;

		@Autowired
		public TestHomeController(CategoryService categoryService, UserService userService
				,  QuestionWithOptionsService questionWithOptionsService
				, SubmittedAnswerMultiService submittedAnswersMultiService, UtilService utilService
				, BatchUpdateService batchUpdateService, AnswerService answerService, MockMvc mockMvc) {
			this.categoryService = categoryService;
			this.userService = userService;
			this.questionWithOptionsService = questionWithOptionsService;
			this.submittedAnswerMultiService = submittedAnswersMultiService;
			this.utilService = utilService;
			this.batchUpdateService = batchUpdateService;
			this.answerService = answerService;
			this.mockMvc = mockMvc;
		}

	
	 	@Test
	 	public void testIndex() throws Exception {
		 mockMvc.perform(get("/home"))
	     .andExpect(status().isOk())
	     .andExpect(view().name("index"))
     .andExpect(forwardedUrl("index.jsp"));

		 
		 
	 }
	 
	

}
