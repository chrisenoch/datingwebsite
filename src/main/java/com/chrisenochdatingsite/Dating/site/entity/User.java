package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswer;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	private String firstName;
	private String surname;
	private String email;
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Enumerated(EnumType.STRING)
	private MembershipType membershipType;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Optional<List<SubmittedAnswer>> submittedAnswers = Optional.empty();
	//Original: private Map<String, SubmittedAnswer> submittedAnswers; //String is questionText. Improve: Change to enum or class.
	@Transient
	private Matcher matcher;

	public User() {
		super();
	}

	public User(Long id, String firstName, String surname, String email, LocalDate dateOfBirth, Sex sex
			, MembershipType memebrshipType) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.membershipType = memebrshipType;
		
	}

	public User(String firstName, String surname, String email, LocalDate dateOfBirth, Sex sex
			, MembershipType memebrshipType) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.membershipType = memebrshipType;
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

	public Optional<List<SubmittedAnswer>>  getSubmittedAnswers() {
		return submittedAnswers;
	}

	public void setSubmittedAnswers(Optional<List<SubmittedAnswer>>  submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}



	public enum Sex{
		MALE, FEMALE
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email
				+ ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", membershipType=" + membershipType + "]";
	}


}


