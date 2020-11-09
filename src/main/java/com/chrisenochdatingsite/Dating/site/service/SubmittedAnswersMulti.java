package com.chrisenochdatingsite.Dating.site.service;

import java.util.Set;

import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.QuestionImpl;

public interface SubmittedAnswersMulti {
	Question getQuestion();
	User getUser();
	Set<Answer> getAnswerOptions();
		
}
