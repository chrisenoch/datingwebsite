package com.chrisenochdatingsite.Dating.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.BatchUpdateDAO;

@Service
public class BatchUpdateServiceImpl implements BatchUpdateService {
	
	@Autowired
	BatchUpdateDAO batchUpDateDAO;
	
	@Override
	@Transactional
	public void batchUpdateMembershipType() {
		batchUpDateDAO.batchUpdateMembershipType();

	}

}
