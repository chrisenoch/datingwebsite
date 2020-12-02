 package com.chrisenochdatingsite.Dating.site.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswersMulti;

@Entity
public class SubmittedAnswerMultiImpl extends SubmittedAnswerImpl implements SubmittedAnswersMulti {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Answer> selectedAnswers; //Might need to include mappedBy here, but to abstract superclass? mappedBy using both answer types?
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
