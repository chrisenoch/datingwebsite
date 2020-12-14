package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.SubmittedAnswerMultiDAO;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;

@Service
public class SubmittedAnswerMultiServiceImpl implements SubmittedAnswerMultiService{

	SubmittedAnswerMultiDAO submittedAnswerMultiDAO;
	
	@Autowired
	public SubmittedAnswerMultiServiceImpl(SubmittedAnswerMultiDAO submittedAnswerMultiDAO) {
		super();
		this.submittedAnswerMultiDAO = submittedAnswerMultiDAO;
	}

	@Override
	@Transactional
	public void save(SubmittedAnswerMultiImpl subAMulti) {
		submittedAnswerMultiDAO.save(subAMulti);

	}

	@Override
	@Transactional
	public List<SubmittedAnswerMultiImpl> findAll() {
		
		return submittedAnswerMultiDAO.findAll();
	}

}
