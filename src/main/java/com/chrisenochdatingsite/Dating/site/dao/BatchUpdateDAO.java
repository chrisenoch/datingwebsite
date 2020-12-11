package com.chrisenochdatingsite.Dating.site.dao;

import com.chrisenochdatingsite.Dating.site.entity.MembershipType;

public interface BatchUpdateDAO {
	
	public void batchUpdateMembershipType(MembershipType typeToBeUpdated, MembershipType newType);

}
