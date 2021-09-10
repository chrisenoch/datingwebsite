package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitable;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitor;

/**
 * This is for radio button answers. It is possible for none of the options to be selected. For instance, if a question asks if the user likes
 * horror movies, the suer could select, "yes," "no," or simply not select anything.
 * @author chris
 *
 */
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
