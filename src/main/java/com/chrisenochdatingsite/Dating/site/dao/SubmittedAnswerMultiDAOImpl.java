package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;

@Repository
public class SubmittedAnswerMultiDAOImpl implements SubmittedAnswerMultiDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SubmittedAnswerMultiImpl> findAll() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<SubmittedAnswerMultiImpl> theQuery =
				currentSession.createQuery("from SubmittedAnswerMultiImpl", SubmittedAnswerMultiImpl.class);
		
		// execute query and get result list
		List<SubmittedAnswerMultiImpl> answers = theQuery.getResultList();
		
		// return the results		
		return answers;
	}

	@Override
	public void save(SubmittedAnswerMultiImpl subAMulti) {
		
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				// save employee
				currentSession.saveOrUpdate(subAMulti);
		
	}
	

}
