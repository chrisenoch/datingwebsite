package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;

public interface SubmittedAnswerMultiDAO {
	
	public void save(SubmittedAnswerMultiImpl subAMulti);
	
	public List<SubmittedAnswerMultiImpl> findAll();

}
