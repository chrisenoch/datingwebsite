package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;
import java.util.function.Function;

public class AnswerVisitorImpl implements AnswerVisitor {

	//@Override
	public void visit(AnswerWeightedImpl answerWeightedImpl, int convertedScore, Answer ans
			, Map<String, Answer> comparedUserSelectedAnswers,  Function<Integer,Integer> convertWeightedAns) {
		AnswerWeightedImpl searchingUserAnsWeighted = (AnswerWeightedImpl) ans;

		AnswerWeightedImpl comparedUserAnswerWeighted = (AnswerWeightedImpl) comparedUserSelectedAnswers.get(searchingUserAnsWeighted.getAnswerText());
		int diffInWeight; 
		if (comparedUserAnswerWeighted != null) {		
			System.out.println("doesn't equal null"); //debugging
			diffInWeight = Math.abs(searchingUserAnsWeighted.getAnswerWeight().getWeight() - comparedUserAnswerWeighted.getAnswerWeight().getWeight());
		
			convertedScore = convertWeightedAns.apply(diffInWeight);
		
		} else {
			System.out.println("equals null so continue"); 
			//throw exception. Do custom exception? / continue loop?
			continue;
		}

	}

	@Override
	public void visit(AnswerImpl answerWeightedImpl) {
		

	}

}
