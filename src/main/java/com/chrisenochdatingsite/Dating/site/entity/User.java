package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;

public class User {

	private Long id;
	private String firstName;
	private String surname;
	private String email;
	private LocalDate dateOfBirth;
	private Sex sex;

	public User(Long id, String firstName, String surname, String email, LocalDate dateOfBirth, Sex sex) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		
	}

	public User(String firstName, String surname, String email, LocalDate dateOfBirth, Sex sex) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}



	enum Sex{
		MALE, FEMALE
	}
	
	

}

