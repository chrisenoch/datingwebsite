package com.chrisenochdatingsite.Dating.site.interfaces;

import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.OpenQuestionImpl;

public interface SubmittedAnswerSingle extends SubmittedAnswer {

	Answer getSelectedAnswer();
	
}
