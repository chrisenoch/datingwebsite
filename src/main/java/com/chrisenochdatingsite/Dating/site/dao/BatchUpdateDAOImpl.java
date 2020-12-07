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
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//Method
	//Arguments: batch size, 
	//select all from db
	//loop and update
	
	//Change to selecting only those that have trial membership .. and paid member = no?
	public void batchUpdateMembershipType() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery(
		        "select distinct u " +
		        "from User u where MembershipType = :MembershipType", User.class).setParameter("MembershipType", "TRIAL")
		    .list();
			
		for (User user : users) {
			user.setMembershipType(MembershipType.BASIC);
			System.out.println("End of a cycle");
		}
	}
	

	
	
	

}
