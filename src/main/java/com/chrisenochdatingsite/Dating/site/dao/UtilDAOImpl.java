package com.chrisenochdatingsite.Dating.site.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UtilDAOImpl implements UtilDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

		return currentSession.getReference(entityClass, primaryKey);
		
	}

}