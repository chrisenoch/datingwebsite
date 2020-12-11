package com.chrisenochdatingsite.Dating.site.service;

import com.chrisenochdatingsite.Dating.site.entity.MembershipType;

public interface BatchUpdateService {
	
	public void batchUpdateMembershipType(MembershipType typeToBeUpdated, MembershipType newType);

}
