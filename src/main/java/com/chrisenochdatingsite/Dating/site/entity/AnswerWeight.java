package com.chrisenochdatingsite.Dating.site.entity;

public enum AnswerWeight {
	ZER0(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

	AnswerWeight(int weight) {
		this.weight = weight;
	}
	
	private int weight;

	public int getWeight() {
		return weight;
	}

	
}
