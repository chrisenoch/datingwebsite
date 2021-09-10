package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitable;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitor;

/**
 * This is for answer options that require a weighted answer. For instance, "Please indicate how much you like X on a scale
 * of 0 to 6, 0 being hate and 6 being love.
 * @author chris
 *
 */
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
