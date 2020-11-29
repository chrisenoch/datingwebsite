package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Map;

import com.chrisenochdatingsite.Dating.site.interfaces.Answer;
import com.chrisenochdatingsite.Dating.site.interfaces.QuestionWithOptions;

public class QuestionWithOptionsImpl implements QuestionWithOptions {

	private int id;
	private String questionText;
	private Map<String, Answer> possibleAnswers; //Improve code: change String to answerText enum
	Category category;
	
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public QuestionWithOptionsImpl(String questionText, Map<String, Answer> possibleAnswers, Category category) {
		this.questionText = questionText;
		this.possibleAnswers = possibleAnswers;
		this.category = category;
	}

	public QuestionWithOptionsImpl(int id, String questionText, Map<String, Answer> possibleAnswers,
			Category category) {
		this.id = id;
		this.questionText = questionText;
		this.possibleAnswers = possibleAnswers;
		this.category = category;
	}

	@Override
	public String toString() {
		return "QuestionWithOptionsImpl [id=" + id + ", questionText=" + questionText + ", possibleAnswers="
				+ possibleAnswers + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionWithOptionsImpl other = (QuestionWithOptionsImpl) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		return true;
	}

	
	

	

}
