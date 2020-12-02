package com.chrisenochdatingsite.Dating.site.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("question_with_options")
public class QuestionWithOptionsImpl extends Question {

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	//Performance Optimisation: Managed from @ManyToOne so as to reduce database calls.
	private List<Answer> possibleAnswers = new ArrayList<>();
	
	public void addPossibleAnswer(Answer answer) {
		possibleAnswers.add(answer);
		answer.setQuestion(this);
	}
	
	public void removepossibleAnswer(Answer answer) {
		possibleAnswers.remove(answer);
		answer.setQuestion(null);
	}
	
	
	public List<Answer> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(List<Answer> possibleAnswers) {
		possibleAnswers.forEach(a-> addPossibleAnswer(a));
	}	

	public QuestionWithOptionsImpl() {
		super();
	}

	public QuestionWithOptionsImpl(int id, String questionText, Category category, List<Answer> possibleAnswers) {
		super(id, questionText, category);
		possibleAnswers.forEach(a-> addPossibleAnswer(a));
		
	}
	
	public QuestionWithOptionsImpl(String questionText, Category category, List<Answer> possibleAnswers) {
		super(questionText, category);
		possibleAnswers.forEach(a-> addPossibleAnswer(a));
	}


}
