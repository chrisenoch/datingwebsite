package com.chrisenochdatingsite.Dating.site.entity;

public interface AnswerVisitor {
	
	public void visit (AnswerWeightedImpl answerWeightedImpl, int convertedScore);
	public void visit (AnswerImpl answerWeightedImpl, int convertedScore);

}
