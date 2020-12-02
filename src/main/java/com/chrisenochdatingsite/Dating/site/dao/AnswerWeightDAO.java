package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

public interface AnswerWeightDAO {
	
	public void save(AnswerWeightedImpl answerWeightedImpl);
	
	public List<Answer> findAll();

}
