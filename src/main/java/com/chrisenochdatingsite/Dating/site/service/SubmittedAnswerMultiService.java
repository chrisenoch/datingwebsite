package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;

public interface SubmittedAnswerMultiService {
	
	public void save(SubmittedAnswerMultiImpl subAMulti);
	
	public List<SubmittedAnswerMultiImpl> findAll();

}
