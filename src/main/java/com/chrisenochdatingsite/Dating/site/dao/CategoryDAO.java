package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import com.chrisenochdatingsite.Dating.site.entity.Category;

public interface CategoryDAO {
	
	void save(Category theCategory);
	
	List<Category> findAll();

}
