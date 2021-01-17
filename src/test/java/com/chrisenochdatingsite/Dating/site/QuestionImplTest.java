package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.OpenQuestionImpl;
import com.chrisenochdatingsite.Dating.site.entity.Question;

public class QuestionImplTest  {

	private String questionText;
	
	@Test
	public void shouldReturnQuestion() {
		
		Category category = Mockito.mock(Category.class);
		Mockito.when(category.getCategory()).thenReturn("Sport");
		
		Question questionText = new OpenQuestionImpl("What is your relationship status?", category);
		
		assertEquals("What is your relationship status?", questionText.getQuestionText());
		assertEquals("Sport", questionText.getCategory().getCategory());
		
	}

}
