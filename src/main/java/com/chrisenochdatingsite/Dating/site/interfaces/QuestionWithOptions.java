package com.chrisenochdatingsite.Dating.site.interfaces;

import java.util.List;

public interface QuestionWithOptions extends Question {
	List<Answer> getPossibleAnswers(); //Improve code: change String to enum?
	
}

 