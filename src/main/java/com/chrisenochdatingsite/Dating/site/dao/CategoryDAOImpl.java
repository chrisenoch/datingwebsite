package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Category theCategory) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save employee
		currentSession.saveOrUpdate(theCategory);
	}
	
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
