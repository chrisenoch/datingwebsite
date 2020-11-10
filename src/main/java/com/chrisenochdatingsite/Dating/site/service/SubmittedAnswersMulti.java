package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.User;

public interface SubmittedAnswersMulti {
	Question getQuestion();
	User getUser();
	List<Answer> getSelectedAnswers();
		
}
