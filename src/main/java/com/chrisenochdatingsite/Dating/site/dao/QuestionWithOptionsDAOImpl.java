package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;

@Repository
public class QuestionWithOptionsDAOImpl implements QuestionWithOptionsDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public QuestionWithOptionsDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(QuestionWithOptionsImpl questionWithOptionsImpl) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save employee
		currentSession.saveOrUpdate(questionWithOptionsImpl);
	}
	
	@Override
	public List<Question> findAll() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Question> theQuery =
				currentSession.createQuery("from Question", Question.class);
		
		// execute query and get result list
		List<Question> questions = theQuery.getResultList();
		
		// return the results		
		return questions;
	}
	

}
