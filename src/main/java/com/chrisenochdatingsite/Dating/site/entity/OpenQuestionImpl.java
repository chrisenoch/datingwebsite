package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//This is for questions with no options. E.g. profile questions where user writes about himself/herself
@Entity
@DiscriminatorValue("open_question")
public class OpenQuestionImpl extends Question{

	public OpenQuestionImpl(String questionText, Category category) {
		super(questionText, category);
	}
	
	


	
	

}
