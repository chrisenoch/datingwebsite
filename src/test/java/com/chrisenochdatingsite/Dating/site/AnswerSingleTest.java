package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.AnswerSingle;
import com.chrisenochdatingsite.Dating.site.service.Answer;

public class AnswerSingleTest {
	
	@Test
	public void shouldReturnAnswer() {
		Answer relationshipStatusAnswer = new AnswerSingle(1, "Single");
		
		assertEquals("Single", relationshipStatusAnswer.getAnswerText());
		
	}

}
