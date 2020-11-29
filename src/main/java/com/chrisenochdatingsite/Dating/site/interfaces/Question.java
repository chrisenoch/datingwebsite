package com.chrisenochdatingsite.Dating.site.interfaces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.chrisenochdatingsite.Dating.site.entity.Category;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public interface Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId();
	public void setId(int id);
	
	public String getQuestionText();  //Improve code: Should this return Answer also?
	public void setQuestionText(String questionText);
	
	@ManyToOne
	public Category getCategory(); //Improve code. Is it necessary to have category option here?
	public void setCategory(Category category);
}
