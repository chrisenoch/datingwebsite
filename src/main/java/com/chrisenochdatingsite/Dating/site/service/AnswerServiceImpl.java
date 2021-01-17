package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.AnswerDAO;
import com.chrisenochdatingsite.Dating.site.entity.Answer;


@Service
public class AnswerServiceImpl implements AnswerService {
	
	AnswerDAO answerImplDAO;
		
	@Autowired
	public AnswerServiceImpl(AnswerDAO answerImplDAO) {
		super();
		this.answerImplDAO = answerImplDAO;
	}

	@Override
	@Transactional
	public void save(Answer answer) {
		answerImplDAO.save(answer);

	}

	@Override
	@Transactional
	public List<Answer> findAll() {
		return answerImplDAO.findAll();
	}

	@Override
	@Transactional
	public Answer getById(long id) {

		return  answerImplDAO.getById(id);
	}

}
