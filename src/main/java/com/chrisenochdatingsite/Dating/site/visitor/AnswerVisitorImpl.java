package com.chrisenochdatingsite.Dating.site.visitor;

import java.util.Map;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;

public class AnswerVisitorImpl implements AnswerVisitor {

	@Override
	public int visit(AnswerWeightedImpl answerWeightedImpl) throws NoEquivalentAnswerException {
		
		AnswerWeightedImpl searchingUserAnsWeighted = answerWeightedImpl;

		AnswerWeightedImpl comparedUserAnswerWeighted = (AnswerWeightedImpl) answerWeightedImpl
				.getAnswerVisitorHelper().getComparedUserSelectedAnswers().get(searchingUserAnsWeighted
						.getAnswerText());
		int diffInWeight; 
		
		int convertedScore;
		if (comparedUserAnswerWeighted != null) {		
			System.out.println("doesn't equal null"); //debugging
			diffInWeight = Math.abs(searchingUserAnsWeighted.getAnswerWeight().getWeight() - comparedUserAnswerWeighted.getAnswerWeight().getWeight());
			System.out.println("Debugging fuctional answerimpl: " + answerWeightedImpl.getAnswerVisitorHelper().getConvertWeightedAns());
			convertedScore = answerWeightedImpl.getAnswerVisitorHelper().getConvertWeightedAns().apply(diffInWeight);
			return convertedScore;
		
		} else {
			System.out.println("equals null so continue"); 
			//throw exception. Do custom exception? / continue loop?
			throw new NoEquivalentAnswerException ("A comparable answer could not be found");
		}

	}

	//@Override
	public int visit (AnswerImpl ansImpl) {
		
		boolean isMatch = scoreAnswerImpls(ansImpl.getAnswerVisitorHelper().getComparedUserSelectedAnswers(), ansImpl);
		System.out.println("Debugging fuctional answerimpl: " + ansImpl.getAnswerVisitorHelper().getConvertCheckboxAns());
		int convertedScore = ansImpl.getAnswerVisitorHelper().getConvertCheckboxAns().apply(isMatch);
		return convertedScore;
		

	}
	
	private boolean scoreAnswerImpls(Map<String, Answer> comparedUserSelectedAnswers, Answer ans) {
		int count = 0;
		boolean isMatch;
		for (Map.Entry<String, Answer> map2 : comparedUserSelectedAnswers.entrySet()) {
			if (map2.getValue() instanceof AnswerImpl && ans.equals(map2.getValue()) ) { //Debugging: Will probably have to override equals method (and thus hash), compare by id
				count++;
			}
		}
		if (count >= 1) {
			//If there should be duplicate answers, the score will only get counted once because after the first time, the entry int he map below ill simply be overwritten.
			//100% match, add to score variable. Score variable will then go through functional interface method to convert the score
			//Add logging for duplicate answers to warn of potential problems? i.e. if count >= 1 ? Great idea, but extra step in the code...
			isMatch = true;
			System.out.println("isMatch: " + isMatch + " " + ans.getAnswerText());
		
		} else  {
			//0% match, add to score variable. Score variable will then go through functional interface method to convert the score
			isMatch = false;
			System.out.println("isMatch: " + isMatch + " " + ans.getAnswerText());
		}
		return isMatch;
	}

}
