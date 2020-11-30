package com.chrisenochdatingsite.Dating.site.interfaces;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;

public interface SubmittedAnswersMulti extends SubmittedAnswer {
	
	List<Answer> getSelectedAnswers(); //Improve code, change String to questionText
		
}
