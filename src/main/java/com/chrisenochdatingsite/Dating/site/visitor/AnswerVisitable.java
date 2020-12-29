package com.chrisenochdatingsite.Dating.site.visitor;

import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;

public interface AnswerVisitable {
	public int accept (AnswerVisitor answerVisitor) throws NoEquivalentAnswerException;

}
