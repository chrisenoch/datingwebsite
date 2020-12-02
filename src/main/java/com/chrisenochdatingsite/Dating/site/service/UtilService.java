package com.chrisenochdatingsite.Dating.site.service;

import org.springframework.stereotype.Service;


public interface UtilService {
	
	<T> T getReference(Class<T> entityClass, Object primaryKey);

}
