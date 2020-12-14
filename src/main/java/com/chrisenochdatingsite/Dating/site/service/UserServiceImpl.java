package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.UserDAO;
import com.chrisenochdatingsite.Dating.site.entity.User;

@Service
public class UserServiceImpl implements UserService {

	UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void save(User theUser) {
		userDAO.save(theUser);

	}

	@Override
	@Transactional
	public List<User> findAll() {
		
		return userDAO.findAll();
	}

}
