package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;


public class AnswersTests {
	
	@Test
	public void shouldReturnAnswerValuesAddedViaConstructor() {
		
		Answer ans1 = Mockito.mock(Answer.class);
		Answer ans2 = Mockito.mock(Answer.class);
		
		Mockito.when(ans1.getAnswerText()).thenReturn("Answer A");
		Mockito.when(ans2.getAnswerText()).thenReturn("Answer B");
		
		User user = Mockito.mock(User.class);
		Question question = Mockito.mock(Question.class);
		
		var subAnsMultImpl = new SubmittedAnswerMultiImpl(question, user, ans1, ans2);
		
		Set<Answer> selectedAnswers = subAnsMultImpl.getSelectedAnswers();
		
		assertEquals(2,selectedAnswers.size());
		
	} 
	
}
