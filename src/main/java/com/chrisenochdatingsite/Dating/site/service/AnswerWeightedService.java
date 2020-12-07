package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

public interface AnswerWeightedService {
	
	public void save(AnswerWeightedImpl answerWeightedImpl);
	
	public List<Answer> findAll();

}
