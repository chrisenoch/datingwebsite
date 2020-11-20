package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.service.Answer;

public class AnswerSingleTest {
	
	@Test
	public void shouldReturnAnswer() {

		Answer relationshipStatusAnswer = new AnswerImpl(1, "Single");
		
		assertEquals("Single", relationshipStatusAnswer.getAnswerText());
		
	}

}
