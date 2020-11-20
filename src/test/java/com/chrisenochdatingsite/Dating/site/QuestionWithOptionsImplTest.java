package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;

import java.util.LinkedHashMap;
import java.util.Map;

import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.service.Answer;

public class QuestionWithOptionsImplTest {
	
	@Test
	@RepeatedTest(5) //Just practising
	public void shouldReturnOnlyElementsAddedInInsertionOrder() {
		Answer answer1 = mock(Answer.class);
		Answer answer2 = mock(Answer.class);
		Answer answer3 = mock(Answer.class);
		
		Category category = mock(Category.class);
		
		Map<String, Answer> possibleAnswers = new LinkedHashMap<>(); //So order of insertion is maintained
		possibleAnswers.put("Answer A", answer1);
		possibleAnswers.put("Answer B", answer2);
				
		var qWithOptions = new QuestionWithOptionsImpl("What is your relationship status?", possibleAnswers, category);
		
		assertThat(qWithOptions.getPossibleAnswers())
        .isNotEmpty()
        .hasSize(2)
       .contains(entry("Answer A", answer1), entry("Answer B", answer2))
        .containsAnyOf(entry("Answer A", answer1), entry("Answer B", answer2), entry("Answer C", answer3))
        .containsExactly(entry("Answer A", answer1), entry("Answer B", answer2))
        //.doesNotContainSequence(entry("Answer B", answer2), entry("Answer A", answer1))
        .doesNotContain(entry("Answer C", answer3));
		
		}

}
