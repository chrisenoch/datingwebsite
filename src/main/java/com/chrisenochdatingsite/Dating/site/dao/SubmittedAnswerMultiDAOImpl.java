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
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public SubmittedAnswerMultiDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<SubmittedAnswerMultiImpl> findAll() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<SubmittedAnswerMultiImpl> theQuery =
				currentSession.createQuery("from SubmittedAnswerMultiImpl", SubmittedAnswerMultiImpl.class);
		
		List<SubmittedAnswerMultiImpl> submittedAnswersMultiImpls = theQuery.getResultList();
			
		return submittedAnswersMultiImpls;
	}

	@Override
	public void save(SubmittedAnswerMultiImpl subAMulti) {

				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(subAMulti);
		
	}
	

}
