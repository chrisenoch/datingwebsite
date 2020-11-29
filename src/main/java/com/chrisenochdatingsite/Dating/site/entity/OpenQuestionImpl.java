package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;

import com.chrisenochdatingsite.Dating.site.interfaces.Answer;
import com.chrisenochdatingsite.Dating.site.interfaces.Question;

//This is for questions with no options. E.g. profile questions where user writes about himself/herself
public class OpenQuestionImpl implements Question{

	private int id;
	private String questionText;
	Category category;
	//Add private Map<String, Answer> possibleAnswers
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public OpenQuestionImpl(String questionText) {
		this.questionText = questionText;
	}

	public OpenQuestionImpl(String questionText, Category category) {
		this.questionText = questionText;
		this.category = category;
	}


	
	

}
