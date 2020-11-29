package com.chrisenochdatingsite.Dating.site.interfaces;

import java.util.Map;

public interface SubmittedAnswersMulti extends SubmittedAnswer {
	
	Map<String, Answer> getSelectedAnswers(); //Improve code, change String to questionText
		
}
