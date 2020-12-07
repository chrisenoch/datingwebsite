package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

@Repository
public class AnswerWeightedDAOImpl implements AnswerWeightedDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(AnswerWeightedImpl answerWeightedImpl) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save employee
		currentSession.saveOrUpdate(answerWeightedImpl);
	}
	
	public List<Answer> findAll() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Answer> theQuery =
				currentSession.createQuery("from Answer", Answer.class);
		
		// execute query and get result list
		List<Answer> answers = theQuery.getResultList();
		
		// return the results		
		return answers;
	}
	

}
