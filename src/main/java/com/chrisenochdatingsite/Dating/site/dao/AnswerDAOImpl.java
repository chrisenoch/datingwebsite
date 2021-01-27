package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Answer;

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

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Answer> theQuery =
				currentSession.createQuery("from Answer", Answer.class);
		
		List<Answer> answers = theQuery.getResultList();
			
		return answers;
	}
	
	@Override
	public Answer getById(long id) {

		Session currentSession = sessionFactory.getCurrentSession();

		Answer answer = currentSession.get(Answer.class, id);
			
		return answer;
	}
	

}
