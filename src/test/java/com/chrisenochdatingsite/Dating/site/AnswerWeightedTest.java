package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static  org.mockito.Mockito.mock;

import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
@Disabled
public class AnswerWeightedTest {
	
	@Test
	public void shouldReturnWeightValueAsIntWhenAnswerWeightInvoked() {
		AnswerWeight answerWeight = AnswerWeight.ZER0;
		
		assertTrue(0 == answerWeight.getWeight());
		
	}
	
	@Test
	public void shouldReturnMatchingAnswerTextAndWeight() {
		Category interests = mock(Category.class);
		
		AnswerWeightedImpl horror = new AnswerWeightedImpl(1, "Horror", AnswerWeight.FIVE, interests); // Answer text should also be enum?
		assertEquals("Horror", horror.getAnswerText());
		assertEquals(5, horror.getAnswerWeight().getWeight());
	}
	
	
	
	
	

}
