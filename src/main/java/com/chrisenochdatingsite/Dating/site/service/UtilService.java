package com.chrisenochdatingsite.Dating.site.service;


public interface UtilService {
	
	<T> T getReference(Class<T> entityClass, Object primaryKey);

}
