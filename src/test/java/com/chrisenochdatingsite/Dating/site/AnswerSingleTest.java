package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

public class AnswerSingleTest {
	
	@Test
	public void shouldReturnAnswer() {

		Answer relationshipStatusAnswer = new AnswerImpl(1, "Single");
		
		assertEquals("Single", relationshipStatusAnswer.getAnswerText());
		
	}

}
