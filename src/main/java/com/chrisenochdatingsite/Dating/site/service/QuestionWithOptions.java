package com.chrisenochdatingsite.Dating.site.service;

import java.util.Set;

public interface QuestionWithOptions extends Question {
	Set<Answer> getAnswerOptions();
	
}
