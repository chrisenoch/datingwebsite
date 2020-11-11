package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswersMulti;

public class SubmittedAnswerMultiImpl extends SubmittedAnswerImpl implements SubmittedAnswersMulti {
	
	private long id;
	private Question question;
	private User user;
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
	
	
	public SubmittedAnswerMultiImpl(Question question, User user, Set<Answer> selectedAnswers) {
		this.question = question;
		this.user = user;
		this.selectedAnswers = selectedAnswers; 
	}
	
	public SubmittedAnswerMultiImpl(Question question, User user, Answer ... selectedAnswers) {
		this.question = question;
		this.user = user;
		this.selectedAnswers = new HashSet<Answer>(Arrays.asList(selectedAnswers));
				
	}
	@Override
	public String toString() {
		return "SubmittedAnswerMultiImpl [id=" + id + ", question=" + question + ", user=" + user + ", selectedAnswers="
				+ selectedAnswers + "]";
	}
	
	
	



	
	

	
	


}
