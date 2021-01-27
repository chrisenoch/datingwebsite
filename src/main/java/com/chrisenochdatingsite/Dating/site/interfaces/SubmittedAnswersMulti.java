package com.chrisenochdatingsite.Dating.site.interfaces;

import java.util.Set;

import com.chrisenochdatingsite.Dating.site.entity.Answer;

public interface SubmittedAnswersMulti extends SubmittedAnswer {
	
	Set<Answer> getSelectedAnswers(); 
		
}
