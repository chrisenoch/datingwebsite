package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.chrisenochdatingsite.Dating.site.entity.User.Sex;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.User;

public class UserTest {
	
	@Test
	public void shouldCreateProfileTestWhenConstructorFieldsWhenprofileTestInitialised(){
		User user = new User(1L, "Chris", "Enoch", "chris@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
		
		assertEquals(1L, user.getId());
		assertEquals("Chris", user.getFirstName());
		assertEquals("Enoch", user.getSurname());
		assertEquals("chris@yahoo.com", user.getEmail());
		assertEquals(LocalDate.now(), user.getDateOfBirth());
		assertEquals(Sex.MALE, user.getSex());
		
	}
	

	
	

}
