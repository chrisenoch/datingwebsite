package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.QuestionWithOptionsDAO;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

@Service
public class QuestionWithoptionsServiceImpl implements QuestionWithOptionsService {

	QuestionWithOptionsDAO questionWithOptionsDAO ;
	
	@Autowired
	public QuestionWithoptionsServiceImpl(QuestionWithOptionsDAO questionWithOptionsDAO) {
		super();
		this.questionWithOptionsDAO = questionWithOptionsDAO;
	}

	@Override
	@Transactional
	public void save(QuestionWithOptionsImpl questionWithOptionsImpl) {
		questionWithOptionsDAO.save(questionWithOptionsImpl);

	}

	@Override
	@Transactional
	public List<Question> findAll() {
		
		return questionWithOptionsDAO.findAll();
	}

}
