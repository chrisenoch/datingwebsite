package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.Question;

public class QuestionImpl implements Question{

	private String questionText;
	
	@Override
	public String getQuestionText() {
		return questionText;
	}

	public QuestionImpl(String questionText) {
		super();
		this.questionText = questionText;
	}
	
	

}
