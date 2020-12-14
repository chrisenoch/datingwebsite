package com.chrisenochdatingsite.Dating.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenochdatingsite.Dating.site.dao.BatchUpdateDAO;
import com.chrisenochdatingsite.Dating.site.entity.MembershipType;

@Service
public class BatchUpdateServiceImpl implements BatchUpdateService {
	
	BatchUpdateDAO batchUpDateDAO;
	
	@Autowired
	public BatchUpdateServiceImpl(BatchUpdateDAO batchUpDateDAO) {
		this.batchUpDateDAO = batchUpDateDAO;
	}



	@Override
	@Transactional
	public void batchUpdateMembershipType(MembershipType typeToBeUpdated, MembershipType newType) {
		batchUpDateDAO.batchUpdateMembershipType(typeToBeUpdated, newType);

	}

}
