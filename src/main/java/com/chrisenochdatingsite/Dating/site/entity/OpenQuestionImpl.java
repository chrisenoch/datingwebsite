package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


//This is for questions with no options. E.g. profile questions where user writes about himself/herself
@Entity
@DiscriminatorValue("open_question")
public class OpenQuestionImpl extends Question{

	public OpenQuestionImpl(String questionText, Category category) {
		super(questionText, category);
	}
	
	


	
	

}
