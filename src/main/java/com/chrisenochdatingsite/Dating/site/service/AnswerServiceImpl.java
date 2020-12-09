package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.AnswerDAO;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerImplDAO;
	
	@Override
	@Transactional
	public void save(Answer answer) {
		answerImplDAO.save(answer);

	}

	@Override
	@Transactional
	public List<Answer> findAll() {
		// TODO Auto-generated method stub
		return answerImplDAO.findAll();
	}

}
