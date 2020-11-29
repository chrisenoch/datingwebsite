package com.chrisenochdatingsite.Dating.site.interfaces;

import com.chrisenochdatingsite.Dating.site.entity.Category;

public interface Question {
	String getQuestionText();  //Improve code: Should this return Answer also?
	Category getCategory(); //Improve code. Is it necessary to have category option here?
}
