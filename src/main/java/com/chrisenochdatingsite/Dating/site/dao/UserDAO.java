package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.User;

public interface UserDAO {
	
	void save(User theUser);
	
	List<User> findAll();

}
