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
			diffInWeight = Math.abs(searchingUserAnsWeighted.getAnswerWeight().getWeight() - comparedUserAnswerWeighted.getAnswerWeight().getWeight());
			convertedScore = answerWeightedImpl.getAnswerVisitorHelper().getConvertWeightedAns().apply(diffInWeight);
			return convertedScore;
		
		} else {
			throw new NoEquivalentAnswerException ("A comparable answer could not be found");
		}

	}

	@Override
	public int visit (AnswerImpl ansImpl) {
		
		boolean isMatch = scoreAnswerImpls(ansImpl.getAnswerVisitorHelper().getComparedUserSelectedAnswers(), ansImpl);
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
			//If there are duplicate answers, the score will only get counted once because after the first time, the entry in the map below will simply be overwritten.
			//100% match, add to score variable. Score variable will then go through functional interface method to convert the score
			//Add logging for duplicate answers to warn of potential problems? i.e. if count >= 1 ? Great idea, but extra step in the code...
			isMatch = true;
		
		} else  {
			//0% match, add to score variable. Score variable will then go through functional interface method to convert the score
			isMatch = false;
		}
		return isMatch;
	}

}
