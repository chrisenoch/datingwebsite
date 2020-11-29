package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisenochdatingsite.Dating.site.dao.CategoryDAO;
import com.chrisenochdatingsite.Dating.site.entity.Category;

@Service
public class CateogoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public void save(Category theCategory) {
		categoryDAO.save(theCategory);

	}

	@Override
	public List<Category> findAll() {
		
		return categoryDAO.findAll();
	}

}
