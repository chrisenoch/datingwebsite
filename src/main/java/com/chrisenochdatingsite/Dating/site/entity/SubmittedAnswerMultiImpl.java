package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chrisenochdatingsite.Dating.site.interfaces.Answer;
import com.chrisenochdatingsite.Dating.site.interfaces.Question;
import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswersMulti;

public class SubmittedAnswerMultiImpl extends SubmittedAnswerImpl implements SubmittedAnswersMulti {
	
	private long id;
	private Question question; 
	private User user;
	private List<Answer> selectedAnswers;
	//private Map<String, Answer> selectedAnswers; //String = answerText. Improve code: Change String to questionText

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
	
	public List<Answer> getSelectedAnswers() {
		return selectedAnswers;
	}
	public void setSelectedAnswers(List<Answer> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}
	public SubmittedAnswerMultiImpl(Question question, User user, List<Answer> selectedAnswers) {
		this.question = question;
		this.user = user;
		this.selectedAnswers = selectedAnswers; 
	}
	
	public SubmittedAnswerMultiImpl(Question question, User user, Answer ... selectedAnswers) {
		this.question = question;
		this.user = user;
		
		this.selectedAnswers = Arrays.asList(selectedAnswers);				
	}
	
	
	@Override
	public String toString() {
		return "SubmittedAnswerMultiImpl [id=" + id + ", question=" + question + ", user=" + user + ", selectedAnswers="
				+ selectedAnswers + "]";
	}
	
	
	



	
	

	
	


}
