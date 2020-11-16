package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.service.Answer;

@Disabled
public class AnswerSingleTest {
	
	@Test
	public void shouldReturnAnswer() {
		
		Category practical = Mockito.mock(Category.class);

		Answer relationshipStatusAnswer = new AnswerImpl(1, "Single", practical);
		
		assertEquals("Single", relationshipStatusAnswer.getAnswerText());
		
	}

}
