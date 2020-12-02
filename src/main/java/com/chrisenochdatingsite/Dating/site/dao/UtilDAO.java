package com.chrisenochdatingsite.Dating.site.dao;

public interface UtilDAO {
	
	<T> T getReference(Class<T> entityClass, Object primaryKey);

}
