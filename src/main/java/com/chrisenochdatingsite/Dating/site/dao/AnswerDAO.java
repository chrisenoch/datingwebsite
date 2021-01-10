package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;

 interface AnswerDAO {
	
	 void save(Answer answer);
	
	 List<Answer> findAll();
	
	 Answer getById(long id);


}
