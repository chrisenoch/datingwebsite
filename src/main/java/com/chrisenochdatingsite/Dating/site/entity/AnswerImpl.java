package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.Entity;

@Entity
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
