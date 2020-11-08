package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.QuestionImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerSingle;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;

public class QuestionImplTest  {

	private String questionText;
	
	@Test
	public void shouldReturnQuestion() {
		Question questionText = new QuestionImpl("What is your relationship status?");
		
		assertEquals("What is your relationship status?", questionText.getQuestionText());
		
	}

}
