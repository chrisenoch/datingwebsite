package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.AnswerWeightedDAO;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

@Service
public class AnswerWeightedServiceImpl implements AnswerWeightedService {

	@Autowired
	AnswerWeightedDAO answerWeightDAO;
	
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
