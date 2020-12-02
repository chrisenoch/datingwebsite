package com.chrisenochdatingsite.Dating.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.UtilDAO;

@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	UtilDAO utilDAO;

	@Override
	@Transactional
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return utilDAO.getReference(entityClass, primaryKey);
	}

}
