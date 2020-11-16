package com.chrisenochdatingsite.Dating.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.chrisenochdatingsite.Dating.site.entity.User;
@Disabled
public class ProfileTest {
	
	@Test
	public void shouldCreateProfileTestWhenConstructorFieldsWhenprofileTestInitialised(){
		User profile = new User(1L, "Chris", "Enoch", "chris@yahoo.com", LocalDate.now());
		
		assertEquals(1L, profile.getId());
		assertEquals("Chris", profile.getFirstName());
		assertEquals("Enoch", profile.getSurname());
		assertEquals("chris@yahoo.com", profile.getEmail());
		assertEquals(LocalDate.now(), profile.getDateOfBirth());
		
	}
	

	
	

}
