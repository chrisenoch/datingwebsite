package com.chrisenochdatingsite.Dating.site.service;

import java.util.Map;

public interface SubmittedAnswersMulti extends SubmittedAnswer {
	
	Map<String, Answer> getSelectedAnswers(); //Improve code, change String to questionText
		
}
