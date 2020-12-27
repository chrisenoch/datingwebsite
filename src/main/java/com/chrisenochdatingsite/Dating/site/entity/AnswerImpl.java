package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("standard_answer")
public class AnswerImpl extends Answer implements AnswerVisitable{

	public AnswerImpl() {
	}
	
	public AnswerImpl(int id, String answerText) {
		super(id, answerText);

	}
	
	public AnswerImpl(String answerText) {
		super(answerText);

	}
	
	

	@Override
	public String toString() {
		return "AnswerImpl [getId()=" + getId() + ", getAnswerText()=" + getAnswerText() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int accept(AnswerVisitor answerVisitor) {
		return answerVisitor.visit(this);
		
	}


}
