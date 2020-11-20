package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static  org.mockito.Mockito.mock;

import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;

public class AnswerWeightedTest {
	
	@Test
	public void shouldReturnMatchingAnswerTextAndWeight() {
		
		AnswerWeightedImpl horror = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE); // Answer text should also be enum?
		assertEquals("Horror", horror.getAnswerText());
		assertEquals(5, horror.getAnswerWeight().getWeight());
	}
	
	
	
	
	

}
