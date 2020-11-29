package com.chrisenochdatingsite.Dating.site.interfaces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.chrisenochdatingsite.Dating.site.entity.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public interface SubmittedAnswer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public long getId();
	
	public void setId(long id);
	
//	public Question getQuestion();
//	public void setQuestion(Question question);
	@ManyToOne
	public User getUser();
	
	public void setUser(User user);
}
