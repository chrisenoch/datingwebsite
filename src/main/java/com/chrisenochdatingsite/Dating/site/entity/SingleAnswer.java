package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Answer;

public class SingleAnswer implements Answer{
	
	int id;
	private String answerText;
	
	@Override
	public String getAnswerText() {
		return this.answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SingleAnswer(int id, String answerText) {
		super();
		this.id = id;
		this.answerText = answerText;
	}
	
	
	
	

}
