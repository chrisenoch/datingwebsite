package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.OpenQuestionImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;

public class QuestionImplTest  {

	private String questionText;
	
	@Test
	public void shouldReturnQuestion() {
		Question questionText = new OpenQuestionImpl(1, "What is your relationship status?");
		
		assertEquals("What is your relationship status?", questionText.getQuestionText());
		
	}

}
