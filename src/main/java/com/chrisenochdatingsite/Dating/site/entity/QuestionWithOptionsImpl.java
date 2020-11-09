package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Set;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptions;

public class QuestionWithOptionsImpl implements QuestionWithOptions {

	private int id;
	private String questionText;
	private Set<Answer> answerOptions;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setAnswerOptions(Set<Answer> answerOptions) {
		this.answerOptions = answerOptions;
	}

	@Override
	public Set<Answer> getAnswerOptions() {
		return answerOptions;
	}

	public QuestionWithOptionsImpl(int id, String questionText, Set<Answer> answerOptions) {
		this.id = id;
		this.questionText = questionText;
		this.answerOptions = answerOptions;
	}



	

}
