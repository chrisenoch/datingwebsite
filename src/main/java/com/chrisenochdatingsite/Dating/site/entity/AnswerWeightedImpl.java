package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.AnswerScaled;

public class AnswerWeightedImpl implements AnswerScaled<AnswerWeight>{

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
	
	@Override
	public Category getCategory() {
		return category;
	}

	public AnswerWeightedImpl(String answerText, AnswerWeight answerWeight, Category category) {
		super();
		this.answerText = answerText;
		this.answerWeight = answerWeight;
		this.category = category;
	}

}
