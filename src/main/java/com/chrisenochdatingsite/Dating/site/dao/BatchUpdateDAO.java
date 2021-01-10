package com.chrisenochdatingsite.Dating.site.dao;

import com.chrisenochdatingsite.Dating.site.entity.MembershipType;

public interface BatchUpdateDAO {
	
	void batchUpdateMembershipType(MembershipType typeToBeUpdated, MembershipType newType);

}
