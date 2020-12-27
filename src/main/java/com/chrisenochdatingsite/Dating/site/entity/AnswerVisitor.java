package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;

public interface AnswerVisitor {
	
	public int visit(AnswerWeightedImpl answerWeightedImpl) throws NoEquivalentAnswerException;
	
	public int visit (AnswerImpl answerImpl);

}
