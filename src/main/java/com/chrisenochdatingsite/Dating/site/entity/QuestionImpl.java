package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;

public class QuestionImpl implements Question{

	private int id;
	private String questionText;
	private Answer selectedAnswer;
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

	public Answer getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Answer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public QuestionImpl(String questionText) {
		this.questionText = questionText;
	}


	
	

}
