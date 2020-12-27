package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;
import java.util.function.Function;

public class AnswerVisitorHelper {
	
	private int convertedScore;
	private Answer ans;
	private Map<String, Answer> comparedUserSelectedAnswers;
	private Function<Boolean,Integer> convertCheckboxAns;
	private Function<Integer,Integer> convertWeightedAns;
	
	
	public AnswerVisitorHelper( Map<String, Answer> comparedUserSelectedAnswers,
			Function<Boolean, Integer> convertCheckboxAns, Function<Integer, Integer> convertWeightedAns) {
		super();
		this.comparedUserSelectedAnswers = comparedUserSelectedAnswers;
		this.convertCheckboxAns = convertCheckboxAns;
		this.convertWeightedAns = convertWeightedAns;
	}
	public Function<Integer, Integer> getConvertWeightedAns() {
		return convertWeightedAns;
	}
	public AnswerVisitorHelper setConvertWeightedAns(Function<Integer, Integer> convertWeightedAns) {
		this.convertWeightedAns = convertWeightedAns;
		return this;
	}
	public int getConvertedScore() {
		return convertedScore;
	}
	public AnswerVisitorHelper setConvertedScore(int convertedScore) {
		this.convertedScore = convertedScore;
		return this;
	}
	public Answer getAns() {
		return ans;
	}
	public AnswerVisitorHelper setAns(Answer ans) {
		this.ans = ans;
		return this;
	}
	
	public Map<String, Answer> getComparedUserSelectedAnswers() {
		return comparedUserSelectedAnswers;
	}
	public AnswerVisitorHelper setComparedUserSelectedAnswers(Map<String, Answer> comparedUserSelectedAnswers) {
		this.comparedUserSelectedAnswers = comparedUserSelectedAnswers;
		return this;
	}
	public Function<Boolean, Integer> getConvertCheckboxAns() {
		return convertCheckboxAns;
	}
	public AnswerVisitorHelper setConvertCheckboxAns(Function<Boolean, Integer> convertCheckboxAns) {
		this.convertCheckboxAns = convertCheckboxAns;
		return this;
	}
	
	
	

}
