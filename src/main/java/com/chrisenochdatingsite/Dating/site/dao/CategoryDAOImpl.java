package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Category theCategory) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save employee
		currentSession.saveOrUpdate(theCategory);
	}
	
	@Override
	public List<Category> findAll() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Category> theQuery =
				currentSession.createQuery("from Category", Category.class);
		
		// execute query and get result list
		List<Category> categories = theQuery.getResultList();
		
		// return the results		
		return categories;
	}
	

}
