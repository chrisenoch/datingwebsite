package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.User;

public interface UserService {
	
public void save(User theUser);
	
	public List<User> findAll();

}
