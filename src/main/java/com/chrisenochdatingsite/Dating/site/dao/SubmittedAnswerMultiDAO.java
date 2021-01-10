package com.chrisenochdatingsite.Dating.site.dao;

import java.util.List;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;

public interface SubmittedAnswerMultiDAO {
	
	void save(SubmittedAnswerMultiImpl subAMulti);
	
	List<SubmittedAnswerMultiImpl> findAll();

}
