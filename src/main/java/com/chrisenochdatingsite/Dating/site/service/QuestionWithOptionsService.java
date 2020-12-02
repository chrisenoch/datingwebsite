package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

public interface QuestionWithOptionsService {
	
	public void save(QuestionWithOptionsImpl q);
	
	public List<Question> findAll();

}
