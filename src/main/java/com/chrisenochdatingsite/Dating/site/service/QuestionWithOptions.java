package com.chrisenochdatingsite.Dating.site.service;

import java.util.Map;

public interface QuestionWithOptions extends Question {
	Map<String, Answer> getPossibleAnswers(); //Improve code: change String to enum?
	
}

 