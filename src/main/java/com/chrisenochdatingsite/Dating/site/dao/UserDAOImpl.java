package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(User theUser) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save employee
		currentSession.saveOrUpdate(theUser);
	}
	
	@Override
	public List<User> findAll() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<User> theQuery =
				currentSession.createQuery("from User", User.class);
		
		// execute query and get result list
		List<User> users= theQuery.getResultList();
		
		// return the results		
		return users;
	}
	

}
