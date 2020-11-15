package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Answer;

public class AnswerImpl implements Answer{
	
	private int id;
	private String answerText; 	
	
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Override
	public String getAnswerText() {
		return this.answerText;
	}

	public AnswerImpl(int id, String answerText) {
		super();
		this.id = id;
		this.answerText = answerText;
	}

	@Override
	public String toString() {
		return "AnswerImpl [id=" + id + ", answerText=" + answerText + "]";
	}

}
