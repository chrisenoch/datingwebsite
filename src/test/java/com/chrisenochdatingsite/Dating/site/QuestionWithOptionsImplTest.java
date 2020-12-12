package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

public class QuestionWithOptionsImplTest {
	
	@Test
	@RepeatedTest(5) //Just practising
	public void shouldReturnOnlyElementsAddedInInsertionOrder() {
		Answer answer1 = mock(Answer.class);
		Answer answer2 = mock(Answer.class);
		Answer answer3 = mock(Answer.class);
		
		Category category = mock(Category.class);
		
		List<Answer> possibleAnswers = Arrays.asList(answer1, answer2);
						
		var qWithOptions = new QuestionWithOptionsImpl("What is your relationship status?", category, possibleAnswers);
		
		assertThat(qWithOptions.getPossibleAnswers())
        .isNotEmpty()
        .hasSize(2)
        .containsExactly(answer1, answer2)
        .doesNotContain(answer3);
		
		}

}
