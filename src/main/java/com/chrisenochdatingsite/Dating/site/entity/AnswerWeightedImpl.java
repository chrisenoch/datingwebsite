package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.AnswerScaled;

public class AnswerWeightedImpl implements AnswerScaled<AnswerWeight>{

	private long id;
	private String answerText;
	private AnswerWeight answerWeight;
	private Category category;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public AnswerWeight getAnswerWeight() {
		return answerWeight;
	}

	public void setAnswerWeight(AnswerWeight answerWeight) {
		this.answerWeight = answerWeight;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}


	public AnswerWeightedImpl(String answerText, Category category) {
		this.answerText = answerText;
		this.category = category;
	}

	public AnswerWeightedImpl(String answerText, AnswerWeight answerWeight, Category category) {
		this.answerText = answerText;
		this.answerWeight = answerWeight;
		this.category = category;
	}

}
