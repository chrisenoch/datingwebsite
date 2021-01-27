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

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theCategory);
	}
	
	@Override
	public List<Category> findAll() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Category> theQuery =
				currentSession.createQuery("from Category", Category.class);
		
		List<Category> categories = theQuery.getResultList();
			
		return categories;
	}
	

}
