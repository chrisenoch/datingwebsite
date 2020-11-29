package com.chrisenochdatingsite.Dating.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.chrisenochdatingsite.Dating.site.interfaces.Answer;

@Entity
public class AnswerImpl implements Answer{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	private String answerText; 	
	
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Override
	public String getAnswerText() {
		return this.answerText;
	}

	public AnswerImpl(int id, String answerText) {
		super();
		this.id = id;
		this.answerText = answerText;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerText == null) ? 0 : answerText.hashCode());
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
		AnswerImpl other = (AnswerImpl) obj;
		if (answerText == null) {
			if (other.answerText != null)
				return false;
		} else if (!answerText.equals(other.answerText))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnswerImpl [id=" + id + ", answerText=" + answerText + "]";
	}

	

}
