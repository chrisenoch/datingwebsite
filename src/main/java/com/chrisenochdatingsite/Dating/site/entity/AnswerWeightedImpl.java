package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("answer_weighted")
public class AnswerWeightedImpl extends Answer{

	private AnswerWeight answerWeight;

	public AnswerWeight getAnswerWeight() {
		return answerWeight;
	}

	public void setAnswerWeight(AnswerWeight answerWeight) {
		this.answerWeight = answerWeight;
	}

	public AnswerWeightedImpl() {
		super();
	}

	public AnswerWeightedImpl(String answerText) {
		super(answerText);
	}

	public AnswerWeightedImpl(String answerText, AnswerWeight answerWeight) {
		super(answerText);
		this.answerWeight = answerWeight;
	}

	@Override
	public String toString() {
		return "AnswerWeightedImpl [answerWeight=" + answerWeight + "]";
	}


}
