 package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.User;

@Repository
public class BatchUpdateDAOImpl implements BatchUpdateDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public BatchUpdateDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void batchUpdateMembershipType(MembershipType typeToBeUpdated, MembershipType newType) {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery(
		        "select distinct u " +
		        "from User u where MembershipType = :MembershipType", User.class).setParameter("MembershipType", typeToBeUpdated.toString())
		    .list();
			
		for (User user : users) {
			user.setMembershipType(newType);
			System.out.println("End of a cycle");
		}
	}
	

	
	
	

}
