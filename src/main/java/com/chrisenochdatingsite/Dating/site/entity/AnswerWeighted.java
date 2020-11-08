package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.AnswerScaled;

public class AnswerWeighted implements AnswerScaled<AnswerWeight>{

	private String answerText;
	private AnswerWeight answerWeight;
	private Category category;
	
	@Override
	public String getAnswerText() {
		return answerText;
	}

	@Override
	public AnswerWeight getWeight() {
		return answerWeight;
	}

	public AnswerWeighted(String answerText, AnswerWeight answerWeight) {
		super();
		this.answerText = answerText;
		this.answerWeight = answerWeight;
	}
	
}
