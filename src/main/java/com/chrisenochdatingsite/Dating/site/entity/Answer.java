package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitable;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitor;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitorHelper;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
//Check constraint added to database to ensure AnswerWeighted always inserts an AnswerWeight value.
@DiscriminatorColumn(name = "answer_type")
public abstract class Answer implements AnswerVisitable{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;
	
	private String answerText; 
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Question question;
	
	@Transient
	private AnswerVisitorHelper answerVisitorHelper;
	
	
	public abstract int accept (AnswerVisitor answerVisitor) throws NoEquivalentAnswerException;

	public AnswerVisitorHelper getAnswerVisitorHelper() {
		return answerVisitorHelper;
	}


	public void setAnswerVisitorHelper(AnswerVisitorHelper answerVisitorHelper) {
		this.answerVisitorHelper = answerVisitorHelper;
	}


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

	
	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public Answer() {
		super();
	}


	public Answer(String answerText) {
		super();
		this.answerText = answerText;
	}


	public Answer(long id, String answerText) {
		super();
		this.id = id;
		this.answerText = answerText;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Answer other = (Answer) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", question=" + question + "]";
	}

	
	
	
	
	
	

}
