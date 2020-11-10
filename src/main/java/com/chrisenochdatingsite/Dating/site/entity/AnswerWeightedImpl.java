package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.AnswerScaled;
import com.chrisenochdatingsite.Dating.site.service.Question;

public class AnswerWeightedImpl implements AnswerScaled<AnswerWeight>{

	private long id;
	private String answerText;
	private AnswerWeight answerWeight;

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

	public AnswerWeightedImpl(String answerText) {
		this.answerText = answerText;
	}

	public AnswerWeightedImpl(String answerText, AnswerWeight answerWeight) {
		this.answerText = answerText;
		this.answerWeight = answerWeight;
	}

}
