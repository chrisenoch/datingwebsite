package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;

@Entity
@DiscriminatorValue("answer_weighted")
public class AnswerWeightedImpl extends Answer implements AnswerVisitable{

	@Enumerated(EnumType.STRING)
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
	
	public AnswerWeightedImpl(long id, String answerText, AnswerWeight answerWeight) {
		super(id, answerText);
		this.answerWeight = answerWeight;
	}

	@Override
	public String toString() {
		return super.toString() + " AnswerWeightedImpl [answerWeight=" + answerWeight + "]";
	}

	@Override
	public int accept(AnswerVisitor answerVisitor) throws NoEquivalentAnswerException {
		return answerVisitor.visit(this);
		
	}




}
