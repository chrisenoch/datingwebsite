package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.chrisenochdatingsite.Dating.site.interfaces.AnswerScaled;

@Entity
public class AnswerWeightedImpl implements AnswerScaled<AnswerWeight>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	private String answerText;
	private AnswerWeight answerWeight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public AnswerWeight getAnswerWeight() {
		return answerWeight;
	}

	public void setAnswerWeight(AnswerWeight answerWeight) {
		this.answerWeight = answerWeight;
	}

	public AnswerWeightedImpl(String answerText) {
		this.answerText = answerText;
	}

	public AnswerWeightedImpl(String answerText, AnswerWeight answerWeight) {
		this.answerText = answerText;
		this.answerWeight = answerWeight;
	}

	@Override
	public String toString() {
		return "AnswerWeightedImpl [id=" + id + ", answerText=" + answerText + ", answerWeight=" + answerWeight + "]";
	}
	
	

}
