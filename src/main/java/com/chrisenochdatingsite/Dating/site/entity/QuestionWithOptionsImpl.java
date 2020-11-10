package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.QuestionWithOptions;

public class QuestionWithOptionsImpl implements QuestionWithOptions {

	private int id;
	private String questionText;
	private Map<String, Answer> possibleAnswers; //Improve code: change String to answerText enum
	private Answer selectedAnswer; //Improve code, abstract class for question?
	
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

	public Map<String, Answer> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(Map<String, Answer> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public Answer getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Answer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public QuestionWithOptionsImpl(String questionText, Map<String, Answer> possibleAnswers) {
		this.questionText = questionText;
		this.possibleAnswers = possibleAnswers;
	}

}