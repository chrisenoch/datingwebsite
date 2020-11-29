package com.chrisenochdatingsite.Dating.site.service;

import java.util.List;
import com.chrisenochdatingsite.Dating.site.entity.Category;

public interface CategoryService {
	
public void save(Category theCategory);
	
	public List<Category> findAll();

}
