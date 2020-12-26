package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;

public interface AnswerDAO {
	
	public void save(Answer answer);
	
	public List<Answer> findAll();
	
	public Answer getById(long id);


}
