package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.chrisenochdatingsite.Dating.site.interfaces.Question;

//This is for questions with no options. E.g. profile questions where user writes about himself/herself
@Entity
public class OpenQuestionImpl implements Question{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private String questionText;
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	private Category category;
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
