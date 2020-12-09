package com.chrisenochdatingsite.Dating.site.entity;

public enum AnswerWeight {
	ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), NULL;

	AnswerWeight(int weight) {
		this.weight = weight;
	}
	
	AnswerWeight() {
	}
	
	private int weight;

	public int getWeight() {
		return weight;
	}

	
}
