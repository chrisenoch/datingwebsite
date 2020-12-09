package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.AnswerImplDAO;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;

@Service
public class AnswerImplServiceImpl implements AnswerImplService {

	@Autowired
	AnswerImplDAO answerImplDAO;
	
	@Override
	@Transactional
	public void save(AnswerImpl answerImpl) {
		answerImplDAO.save(answerImpl);

	}

	@Override
	@Transactional
	public List<Answer> findAll() {
		// TODO Auto-generated method stub
		return answerImplDAO.findAll();
	}

}
