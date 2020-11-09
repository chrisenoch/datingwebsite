package com.chrisenochdatingsite.Dating.site.entity;

import com.chrisenochdatingsite.Dating.site.service.AnswerCategorised;

public class AnswerImpl implements AnswerCategorised{
	
	private int id;
	private String answerText; 
	Category category;
	
	
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Category getCategory() {
		return category;
	}
	
	@Override
	public String getAnswerText() {
		return this.answerText;
	}

	public AnswerImpl(int id, String answerText, Category category) {
		super();
		this.id = id;
		this.answerText = answerText;
		this.category = category;
	}

}
