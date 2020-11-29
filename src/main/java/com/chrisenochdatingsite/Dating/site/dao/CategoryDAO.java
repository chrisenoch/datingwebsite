package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Category;

public interface CategoryDAO {
	
	public void save(Category theCategory);
	
	public List<Category> findAll();

}
