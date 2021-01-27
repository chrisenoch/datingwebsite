 package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswersMulti;

@Entity
public class SubmittedAnswerMultiImpl implements SubmittedAnswersMulti {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="question_id")
	private Question question; 
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinTable(name = "submittedanswermultiimpl_answer",
			joinColumns = {@JoinColumn(name = "SubmittedAnswerMultiImpl_id")},
			inverseJoinColumns = {@JoinColumn(name = "selectedAnswers_id")}		
			)
	private Set<Answer> selectedAnswers; 

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Answer> getSelectedAnswers() {
		return selectedAnswers;
	}
	public void setSelectedAnswers(Set<Answer> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}

	public SubmittedAnswerMultiImpl() {
		super();
	}
	public SubmittedAnswerMultiImpl(Question question, User user, Set<Answer> selectedAnswers) {
		this.question = question;
		this.user = user;
		this.selectedAnswers = selectedAnswers; 
	}
	
	public SubmittedAnswerMultiImpl(Question question, User user, Answer ... selectedAnswers) {
		this.question = question;
		this.user = user;
		
		this.selectedAnswers = Arrays.stream(selectedAnswers).collect(Collectors.toSet());	
	}
	
	
	@Override
	public String toString() {
		return "SubmittedAnswerMultiImpl [id=" + id + ", question=" + question + ", user=" + user + ", selectedAnswers="
				+ selectedAnswers + "]";
	}
	
	
	



	
	

	
	


}
