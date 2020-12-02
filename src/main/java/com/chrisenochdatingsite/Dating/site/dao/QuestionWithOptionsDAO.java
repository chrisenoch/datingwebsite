package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

public interface QuestionWithOptionsDAO {
	
	public void save(QuestionWithOptionsImpl q);
	
	public List<Question> findAll();

}
