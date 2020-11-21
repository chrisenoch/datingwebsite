package com.chrisenochdatingsite.Dating.site;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswer;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserWithSubmittedAnswersParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Parameter parameter = parameterContext.getParameter();
		
		return Objects.equals(parameter.getParameterizedType().
		getTypeName(), "java.util.Map<java.lang.String, com.chrisenochdatingsite.Dating.site.entity.User>");
		
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		
		Category movies = new Category( "Movies");
		Category sports = new Category("Sports");
		Category travel = new Category( "Travel");
		
		//Set up answer objects ready to insert into QuestionWithObjects object constructor
		//Weight not set because at this point because at first the answer objects will be added to question class as possible answers.
		//Weight selected at runtime by user.
		
		var horror = new AnswerWeightedImpl("Horror");
		var action =  new AnswerWeightedImpl("Action");
		var romance = new AnswerWeightedImpl("Romance");
		
		Map<String, Answer> movieAnswerOptions = new HashMap<>();
		movieAnswerOptions.put(horror.getAnswerText(), horror);
		movieAnswerOptions.put(action.getAnswerText(), action);
		movieAnswerOptions.put(romance.getAnswerText(), romance);  //Good candidate for test.

		var basketball = new AnswerWeightedImpl("Basketball");
		var football = new AnswerWeightedImpl("Football");
		var swimming = new AnswerWeightedImpl("Swimming");
			
		Map<String, Answer> sportsAnswerOptions = new HashMap<>();
		sportsAnswerOptions.put(basketball.getAnswerText(), basketball);
		sportsAnswerOptions.put(football.getAnswerText(), football);
		sportsAnswerOptions.put(swimming.getAnswerText(), swimming);
		
		var hiking = 	new AnswerWeightedImpl("Hiking");
		var sightseeing =  new AnswerWeightedImpl("Sightseeing");
		var camping =  new AnswerWeightedImpl("Camping");

		Map<String, Answer> travelAnswerOptions = new HashMap<>();
		travelAnswerOptions.put(hiking.getAnswerText(), hiking);
		travelAnswerOptions.put(sightseeing.getAnswerText(), sightseeing);
		travelAnswerOptions.put(camping.getAnswerText(), camping);
		
		//Set up questions objects ready to be inserted into SubmitAnswer constructors
		var questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
				, movieAnswerOptions, movies);
		var questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
				, sportsAnswerOptions, sports);
		var questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
				, travelAnswerOptions, travel);
		
		User dave = new User(1L, "Dave", "Smith", "dave@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
		User jane = new User(2L, "Jane", "Jones", "jane@yahoo.com", LocalDate.of(1984,  10,  24), Sex.FEMALE);
		User peter = new User(3L, "Peter", "Hanks", "peter@yahoo.com", LocalDate.of(1982,  8,  22), Sex.MALE);
		
		//USER 1
		//Create answer objects with weight
		//Set selected answers for questions objects. Set here to reflect real-life flow of control.
//
//		var movieChoiceDave1 = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE);
//		var movieChoiceDave2 = new AnswerWeightedImpl("Action", AnswerWeight.FOUR);
//		var movieChoiceDave3 = new AnswerWeightedImpl("Romance", AnswerWeight.THREE);
		var movieChoiceDave1 = new AnswerImpl(1, "Horror");
		var movieChoiceDave2 = new AnswerImpl(2, "Action");
		var movieChoiceDave3 = new AnswerImpl(3,"Romance");
		
		var sportsChoiceDave1 = new AnswerWeightedImpl("Football", AnswerWeight.THREE);
		var sportsChoiceDave2 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
		var sportsChoiceDave3 = new AnswerWeightedImpl("Basketball", AnswerWeight.SIX);
		var travelStyleChoiceDave1= new AnswerWeightedImpl("Camping", AnswerWeight.TWO);
		var travelStyleChoiceDave2= new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE);
		var travelStyleChoiceDave3= new AnswerWeightedImpl("Sightseeing", AnswerWeight.ONE);
		
		var submittedAnsDaveMovies1 = new SubmittedAnswerMultiImpl(questionMovies, dave, movieChoiceDave1, movieChoiceDave2, movieChoiceDave3);
		var submittedAnsDaveSports1 = new SubmittedAnswerMultiImpl(questionSports, dave, sportsChoiceDave1, sportsChoiceDave2, sportsChoiceDave3);
		var submittedAnsDaveTravel1 = new SubmittedAnswerMultiImpl(questionTravel, dave, travelStyleChoiceDave1, travelStyleChoiceDave2,travelStyleChoiceDave3);
		
		Map<String, SubmittedAnswer> daveAns = new HashMap<>();
		daveAns.put(submittedAnsDaveMovies1.getQuestion().getQuestionText(), submittedAnsDaveMovies1);
		daveAns.put(submittedAnsDaveSports1.getQuestion().getQuestionText(), submittedAnsDaveSports1);
		daveAns.put(submittedAnsDaveTravel1.getQuestion().getQuestionText(), submittedAnsDaveTravel1);
		
		dave.setSubmittedAnswers(daveAns);
		
		//USER 2
//		var movieChoiceJane1 = new AnswerWeightedImpl("Horror", AnswerWeight.SIX); //Improve code: Could mistakingly add a string that does not exist as answer option.
//		var movieChoiceJane2 = new AnswerWeightedImpl("Action", AnswerWeight.FIVE);
//		var movieChoiceJane3 = new AnswerWeightedImpl("Romance", AnswerWeight.FOUR);
		var movieChoiceJane1 = new AnswerImpl(1, "Horror"); //Improve code: Could mistakingly add a string that does not exist as answer option.
		var movieChoiceJane2 = new AnswerImpl(2, "Action");
		var movieChoiceJane3 = new AnswerImpl(3, "Romance");
		
		var sportsChoiceJane1 = new AnswerWeightedImpl("Basketball", AnswerWeight.ZERO);
		var sportsChoiceJane2 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
		var sportsChoiceJane3 = new AnswerWeightedImpl("Football", AnswerWeight.SIX);	
		var travelStyleChoiceJane1 = new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE);
		var travelStyleChoiceJane2 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.FIVE);
		var travelStyleChoiceJane3 = new AnswerWeightedImpl("Camping", AnswerWeight.FIVE);

		var submittedAnsJaneMovies1 = new SubmittedAnswerMultiImpl(questionMovies, jane, movieChoiceJane1);
		var submittedAnsJaneSports1 = new SubmittedAnswerMultiImpl(questionSports, jane, sportsChoiceJane1,sportsChoiceJane2, sportsChoiceJane3);
		var submittedAnsJaneTravel1 = new SubmittedAnswerMultiImpl(questionTravel, jane, travelStyleChoiceJane1, travelStyleChoiceJane2, travelStyleChoiceJane3);
			
		Map<String, SubmittedAnswer> janeAns = new HashMap<>();
		janeAns.put(submittedAnsJaneMovies1.getQuestion().getQuestionText(), submittedAnsJaneMovies1);
		janeAns.put(submittedAnsJaneSports1.getQuestion().getQuestionText(), submittedAnsJaneSports1);
		janeAns.put(submittedAnsJaneTravel1.getQuestion().getQuestionText(), submittedAnsJaneTravel1 );
		
		jane.setSubmittedAnswers(janeAns); 
		
		//USER 3
//		var movieChoicePeter1 = new AnswerWeightedImpl("Horror", AnswerWeight.ZERO); //Improve code: Could mistakingly add a string that does not exist as answer option.
//		var movieChoicePeter2 = new AnswerWeightedImpl("Romance", AnswerWeight.ZERO);
//		var movieChoicePeter3 = new AnswerWeightedImpl("Action", AnswerWeight.SIX);
		var movieChoicePeter1 = new AnswerImpl(1, "Horror"); //Improve code: Could mistakingly add a string that does not exist as answer option.
		var movieChoicePeter2 = new AnswerImpl(2, "Action");
		var movieChoicePeter3 = new AnswerImpl(3, "Romance");
		

		var sportsChoicePeter1 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
		var sportsChoicePeter2 = new AnswerWeightedImpl("Football", AnswerWeight.FIVE);
		var sportsChoicePeter3 = new AnswerWeightedImpl("Basketball", AnswerWeight.FOUR);
		
		var travelStyleChoicePeter1 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.SIX);
		var travelStyleChoicePeter2 = new AnswerWeightedImpl("Camping", AnswerWeight.SIX);
		var travelStyleChoicePeter3 = new AnswerWeightedImpl("Hiking", AnswerWeight.THREE);
		
		var submittedAnsPeterMovies1 = new SubmittedAnswerMultiImpl(questionMovies, peter, movieChoicePeter2, movieChoicePeter3);
		var submittedAnsPeterSports1 = new SubmittedAnswerMultiImpl(questionSports, peter,sportsChoicePeter1, sportsChoicePeter2, sportsChoicePeter3);
		var submittedAnsPeterTravel1 = new SubmittedAnswerMultiImpl(questionTravel, peter, travelStyleChoicePeter1, travelStyleChoicePeter2, travelStyleChoicePeter3);
		
		Map<String, SubmittedAnswer> peterAns = new HashMap<>();
		peterAns.put(submittedAnsPeterMovies1.getQuestion().getQuestionText(), submittedAnsPeterMovies1);
		peterAns.put(submittedAnsPeterSports1 .getQuestion().getQuestionText(), submittedAnsPeterSports1);
		peterAns.put(submittedAnsPeterTravel1.getQuestion().getQuestionText(), submittedAnsPeterTravel1);
		
		peter.setSubmittedAnswers(peterAns);
		
		Map<String, User> users = new HashMap<>();
		users.put("Dave", dave);
		users.put("Jane", jane);
		users.put("Peter", peter);		
		
		return users;
	
	}
			

}
