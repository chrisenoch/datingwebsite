package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;

@Repository
public class AnswerDAOImpl implements AnswerDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public AnswerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	@Override
	public void save(Answer answer) {
	
		Session currentSession = sessionFactory.getCurrentSession();
				
		currentSession.saveOrUpdate(answer);
	}
	
	@Override
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
	
	@Override
	public Answer getById(long id) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Answer answer = currentSession.get(Answer.class, id);
		
		// return the results		
		return answer;
	}
	

}
