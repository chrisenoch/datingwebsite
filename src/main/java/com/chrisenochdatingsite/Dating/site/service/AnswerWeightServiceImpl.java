package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.AnswerWeightDAO;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

@Service
public class AnswerWeightServiceImpl implements AnswerWeightService {

	@Autowired
	AnswerWeightDAO answerWeightDAO;
	
	@Override
	@Transactional
	public void save(AnswerWeightedImpl answerWeightedImpl) {
		answerWeightDAO.save(answerWeightedImpl);

	}

	@Override
	@Transactional
	public List<Answer> findAll() {
		// TODO Auto-generated method stub
		return answerWeightDAO.findAll();
	}

}
