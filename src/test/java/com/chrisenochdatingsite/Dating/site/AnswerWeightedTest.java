package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeighted;

public class AnswerWeightedTest {
	
	@Test
	public void shouldReturnWeightValueAsIntWhenAnswerWeightInvoked() {
		AnswerWeight answerWeight = AnswerWeight.ZER0;
		
		assertTrue(0 == answerWeight.getWeight());
		
	}
	
	@Test
	public void shouldReturnMatchingAnswerTextAndWeight() {
		AnswerWeighted horror = new AnswerWeighted("Horror", AnswerWeight.FIVE); // Answer text should also be enum?
		assertEquals("Horror", horror.getAnswerText());
		assertEquals(5, horror.getWeight().getWeight());
	}
	
	
	
	
	

}
