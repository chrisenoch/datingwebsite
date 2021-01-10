package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

public interface QuestionWithOptionsDAO {
	
	void save(QuestionWithOptionsImpl q);
	
	List<Question> findAll();

}
