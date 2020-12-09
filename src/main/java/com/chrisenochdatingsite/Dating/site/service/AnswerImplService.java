package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

public interface AnswerImplService {
	
	public void save(AnswerImpl answerImpl);
	
	public List<Answer> findAll();

}
