package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("standard_answer")
public class AnswerImpl extends Answer{

	public AnswerImpl(int id, String answerText) {
		super(id, answerText);

	}

	@Override
	public String toString() {
		return "AnswerImpl [getId()=" + getId() + ", getAnswerText()=" + getAnswerText() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}


}
