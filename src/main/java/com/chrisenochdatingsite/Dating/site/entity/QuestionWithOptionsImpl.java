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
	//@JoinColumn(name= "question_id") //Problem is it gets added to Answers table not the child answers tables.
	//Maybe add a mappedBy here. But would have to include possibleAnswers in abstract superclass?
	private List<Answer> possibleAnswers = new ArrayList<>();
	//private Map<String, Answer> possibleAnswers; //Improve code: change String to answerText enum
	
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
