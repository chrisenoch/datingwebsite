package com.chrisenochdatingsite.Dating.site.service;

import java.util.Set;

import com.chrisenochdatingsite.Dating.site.entity.User;

public interface SubmittedAnswersMulti {
	Question getQuestion();
	User getUser();
	Set<Answer> getSelectedAnswers();
		
}
