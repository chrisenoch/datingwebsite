package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;

public interface AnswerService {
	
	public void save(Answer answer);
	
	public List<Answer> findAll();
	
	public Answer getById(long id);

}
