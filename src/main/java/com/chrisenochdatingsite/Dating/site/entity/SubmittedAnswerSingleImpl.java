package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswerSingle;

public class SubmittedAnswerSingleImpl  extends SubmittedAnswer implements SubmittedAnswerSingle  {
	
	private long id;
	private Question question;
	private Answer answer;
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
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SubmittedAnswerSingleImpl(long id, Question question, Answer answer, User user) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.user = user;
	}
	
	public SubmittedAnswerSingleImpl(Question question, Answer answer, User user) {
		this.question = question;
		this.answer = answer;
		this.user = user;
	}
	

}
