package com.chrisenochdatingsite.Dating.site.visitor;

import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;

public interface AnswerVisitor {
	
	public int visit(AnswerWeightedImpl answerWeightedImpl) throws NoEquivalentAnswerException;
	
	public int visit (AnswerImpl answerImpl);

}
