package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.User;

public interface UserDAO {
	
	public void save(User theUser);
	
	public List<User> findAll();

}
