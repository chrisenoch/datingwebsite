package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
	}
	
	@Override
	public List<User> findAll() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery =
				currentSession.createQuery("from User", User.class);
		
		List<User> users= theQuery.getResultList();
			
		return users;
	}
	

}
