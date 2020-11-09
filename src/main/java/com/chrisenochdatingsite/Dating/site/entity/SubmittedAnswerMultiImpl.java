package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Set;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswersMulti;

public class SubmittedAnswerMultiImpl extends SubmittedAnswer implements SubmittedAnswersMulti {
	
	private long id;
	private Question question;
	private User user;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public SubmittedAnswerMultiImpl(Question question, User user) {
		this.question = question;
		this.user = user;
	}
	
	
	

	
	


}
