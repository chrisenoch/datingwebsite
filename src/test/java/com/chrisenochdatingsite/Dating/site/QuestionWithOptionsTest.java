package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.service.Answer;
@Disabled
public class QuestionWithOptionsTest {
	
	@Test
	@RepeatedTest(20)
	public void shouldReturnOnlyElementsAddedInInsertionOrder() {
		Answer answer1 = mock(Answer.class);
		Answer answer2 = mock(Answer.class);
		Answer answer3 = mock(Answer.class);
		
		Set<Answer> possibleAnswers = new LinkedHashSet<>(); //So order of insertion is maintained
		possibleAnswers.add(answer1);
		possibleAnswers.add(answer2);
				
		var qWithOptions = new QuestionWithOptionsImpl(1, "What is your relationship status?", possibleAnswers);
		
		assertThat(qWithOptions.getAnswerOptions())
        .isNotEmpty()
        .hasSize(2)
        .contains(answer1, answer2)
        .containsAnyOf(answer1, answer2, answer3)
        .containsExactly(answer1, answer2)
        .doesNotContainSequence(answer2, answer1)
        .doesNotContain(answer3);
		
		}

}
