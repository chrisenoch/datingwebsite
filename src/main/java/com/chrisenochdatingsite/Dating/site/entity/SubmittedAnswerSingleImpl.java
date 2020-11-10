package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerSingle;

public class SubmittedAnswerSingleImpl  extends SubmittedAnswer implements SubmittedAnswerSingle  {
	
	private long id;
	private Question question;
	private User user;
	private Answer selectedAnswer;
	
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

	public Answer getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(Answer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	
	public SubmittedAnswerSingleImpl(Question question, User user, Answer selectedAnswer) {
		this.question = question;
		this.user = user;
		this.selectedAnswer = selectedAnswer;
	}
	

}
