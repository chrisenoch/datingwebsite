package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Question;

public class QuestionImpl implements Question{

	private int id;
	private String questionText;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public QuestionImpl(int id, String questionText) {
		super();
		this.id = id;
		this.questionText = questionText;
	}

	public QuestionImpl(String questionText) {
		this.questionText = questionText;
	}

}
