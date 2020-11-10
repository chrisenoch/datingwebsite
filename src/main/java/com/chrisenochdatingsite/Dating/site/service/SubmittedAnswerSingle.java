package com.chrisenochdatingsite.Dating.site.service;

import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.OpenQuestionImpl;

public interface SubmittedAnswerSingle {
	Question getQuestion();
	User getUser();
	Answer getSelectedAnswer();
	
}
