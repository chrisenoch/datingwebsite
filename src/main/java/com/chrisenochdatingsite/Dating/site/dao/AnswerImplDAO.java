package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

public interface AnswerImplDAO {
	
	public void save(AnswerImpl answerImpl);
	
	public List<Answer> findAll();

}
