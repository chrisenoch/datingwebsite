package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.CategoryDAO;
import com.chrisenochdatingsite.Dating.site.entity.Category;

@Service
public class CateogoryServiceImpl implements CategoryService {

	CategoryDAO categoryDAO;
	
	@Autowired
	public CateogoryServiceImpl(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	@Transactional
	public void save(Category theCategory) {
		categoryDAO.save(theCategory);

	}

	@Override
	@Transactional
	public List<Category> findAll() {
		
		return categoryDAO.findAll();
	}

}
