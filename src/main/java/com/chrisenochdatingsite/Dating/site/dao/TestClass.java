package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TestClass {
	@Autowired
	EmployeeDAOHibernateImpl test;
	
	public static void main(String[] args) {
		
		
		List<Employee> employees = test.findAll();
		
		
	}
	
	
}
