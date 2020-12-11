package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

public interface AnswerService {
	
	public void save(Answer answer);
	
	public List<Answer> findAll();
	
	public Answer getById(long id);

}
