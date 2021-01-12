package com.chrisenochdatingsite.Dating.site.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeight;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Matcher;
import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.SubmittedAnswerMultiImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswer;


public class DemoApp {
	
			private static Map<String, User> init() {
				
				Category movies = new Category(1, "Movies");
				Category sports = new Category(2, "Sports");
				Category travel = new Category(3, "Travel");
				
				//Set up answer objects ready to insert into QuestionWithOptionsImplconstructor
				//Weight not set at this point because at first the answer objects will be added to question class as possible answers.
				//Weight selected at runtime by user.
				var horror = new AnswerWeightedImpl("Horror");
				var action =  new AnswerWeightedImpl("Action");
				var romance = new AnswerWeightedImpl("Romance");
				
				List<Answer> movieAnswerOptions = Arrays.asList(horror, action, romance);

				var basketball = new AnswerWeightedImpl("Basketball");
				var football = new AnswerWeightedImpl("Football");
				var swimming = new AnswerWeightedImpl("Swimming");
					
				List<Answer> sportsAnswerOptions = Arrays.asList(basketball, football, swimming);	
				
				var hiking = 	new AnswerWeightedImpl("Hiking");
				var sightseeing =  new AnswerWeightedImpl("Sightseeing");
				var camping =  new AnswerWeightedImpl("Camping");
		
				List<Answer> travelAnswerOptions = Arrays.asList(hiking, sightseeing, camping);
				
				//Set up questions objects ready to be inserted into SubmitAnswer constructors
				var questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
						, movies, movieAnswerOptions);
				var questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
						, sports, sportsAnswerOptions);
				var questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
						, travel, travelAnswerOptions);
				
				User dave = new User(1L, "Dave", "Smith", "dave@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE, MembershipType.TRIAL);
				User jane = new User(2L, "Jane", "Jones", "jane@yahoo.com", LocalDate.of(1984,  10,  24), Sex.FEMALE, MembershipType.TRIAL);
				User peter = new User(3L, "Peter", "Hanks", "peter@yahoo.com", LocalDate.of(1982,  8,  22), Sex.MALE, MembershipType.TRIAL);
				
				//USER 1
				//Create answer objects with weight
				//Set selected answers for questions objects. Set here to reflect real-life flow of control.
	//
//				var movieChoiceDave1 = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE);
//				var movieChoiceDave2 = new AnswerWeightedImpl("Action", AnswerWeight.FOUR);
//				var movieChoiceDave3 = new AnswerWeightedImpl("Romance", AnswerWeight.THREE);
				var movieChoiceDave1 = new AnswerImpl(1, "Horror");
				var movieChoiceDave2 = new AnswerImpl(2, "Action");
				var movieChoiceDave3 = new AnswerImpl(3,"Romance");
				
				var sportsChoiceDave1 = new AnswerWeightedImpl(4, "Football", AnswerWeight.THREE);
				var sportsChoiceDave2 = new AnswerWeightedImpl(5, "Swimming", AnswerWeight.ONE);
				var sportsChoiceDave3 = new AnswerWeightedImpl(6, "Basketball", AnswerWeight.SIX);
				var travelStyleChoiceDave1= new AnswerWeightedImpl(7, "Camping", AnswerWeight.TWO);
				var travelStyleChoiceDave2= new AnswerWeightedImpl(8, "Hiking", AnswerWeight.FIVE);
				var travelStyleChoiceDave3= new AnswerWeightedImpl(9, "Sightseeing", AnswerWeight.ONE);
				
				var submittedAnsDaveMovies1 = new SubmittedAnswerMultiImpl(questionMovies, dave, movieChoiceDave1, movieChoiceDave2, movieChoiceDave3);
				var submittedAnsDaveSports1 = new SubmittedAnswerMultiImpl(questionSports, dave,  sportsChoiceDave2, sportsChoiceDave3);
				var submittedAnsDaveTravel1 = new SubmittedAnswerMultiImpl(questionTravel, dave, travelStyleChoiceDave1, travelStyleChoiceDave2,travelStyleChoiceDave3);
				
				List<SubmittedAnswer> daveAns = Arrays.asList(submittedAnsDaveMovies1, submittedAnsDaveSports1,submittedAnsDaveTravel1);
				
				dave.setSubmittedAnswers(daveAns);
				
				//USER 2
//				var movieChoiceJane1 = new AnswerWeightedImpl("Horror", AnswerWeight.SIX); //Improve code: Could mistakingly add a string that does not exist as answer option.
//				var movieChoiceJane2 = new AnswerWeightedImpl("Action", AnswerWeight.FIVE);
//				var movieChoiceJane3 = new AnswerWeightedImpl("Romance", AnswerWeight.FOUR);
				var movieChoiceJane1 = new AnswerImpl(1, "Horror"); //Improve code: Could mistakingly add a string that does not exist as answer option.
				var movieChoiceJane2 = new AnswerImpl(2, "Action");
				var movieChoiceJane3 = new AnswerImpl(3, "Romance");
				
				var sportsChoiceJane1 = new AnswerWeightedImpl("Basketball", AnswerWeight.ZERO);
				var sportsChoiceJane2 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
				var sportsChoiceJane3 = new AnswerWeightedImpl("Football", AnswerWeight.SIX);	
				var travelStyleChoiceJane1 = new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE);
				var travelStyleChoiceJane2 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.FIVE);
				var travelStyleChoiceJane3 = new AnswerWeightedImpl("Camping", AnswerWeight.FIVE);

				var submittedAnsJaneMovies1 = new SubmittedAnswerMultiImpl(questionMovies, jane, movieChoiceJane1, movieChoiceJane3);
				var submittedAnsJaneSports1 = new SubmittedAnswerMultiImpl(questionSports, jane, sportsChoiceJane1,sportsChoiceJane2, sportsChoiceJane3);
				var submittedAnsJaneTravel1 = new SubmittedAnswerMultiImpl(questionTravel, jane, travelStyleChoiceJane1, travelStyleChoiceJane2, travelStyleChoiceJane3);
	 			
				List<SubmittedAnswer> janeAns = Arrays.asList(submittedAnsJaneMovies1, submittedAnsJaneSports1,submittedAnsJaneTravel1 );	
				
				jane.setSubmittedAnswers(janeAns); 
				
				//USER 3
//				var movieChoicePeter1 = new AnswerWeightedImpl("Horror", AnswerWeight.ZERO); //Improve code: Could mistakingly add a string that does not exist as answer option.
//				var movieChoicePeter2 = new AnswerWeightedImpl("Romance", AnswerWeight.ZERO);
//				var movieChoicePeter3 = new AnswerWeightedImpl("Action", AnswerWeight.SIX);
				var movieChoicePeter1 = new AnswerImpl(1, "Horror"); //Improve code: Could mistakingly add a string that does not exist as answer option.
				var movieChoicePeter2 = new AnswerImpl(2, "Action");
				var movieChoicePeter3 = new AnswerImpl(3, "Romance");
				
		
				var sportsChoicePeter1 = new AnswerWeightedImpl(10, "Swimming", AnswerWeight.ONE);
				var sportsChoicePeter2 = new AnswerWeightedImpl(11, "Football", AnswerWeight.FIVE);
				var sportsChoicePeter3 = new AnswerWeightedImpl(12, "Basketball", AnswerWeight.FOUR);
				
				var travelStyleChoicePeter1 = new AnswerWeightedImpl(13, "Sightseeing", AnswerWeight.SIX);
				var travelStyleChoicePeter2 = new AnswerWeightedImpl(14, "Camping", AnswerWeight.SIX);
				var travelStyleChoicePeter3 = new AnswerWeightedImpl(15, "Hiking", AnswerWeight.THREE);
				
				var submittedAnsPeterMovies1 = new SubmittedAnswerMultiImpl(questionMovies, peter, movieChoicePeter2, movieChoicePeter3);
				var submittedAnsPeterSports1 = new SubmittedAnswerMultiImpl(questionSports, peter, sportsChoicePeter1, sportsChoicePeter2, sportsChoicePeter3);
				var submittedAnsPeterTravel1 = new SubmittedAnswerMultiImpl(questionTravel, peter, travelStyleChoicePeter1, travelStyleChoicePeter2, travelStyleChoicePeter3);
				
				List<SubmittedAnswer>  peterAns = Arrays.asList(submittedAnsPeterMovies1, submittedAnsPeterSports1, submittedAnsPeterTravel1);				
				
				peter.setSubmittedAnswers(peterAns);
				
				Map<String, User> users = new HashMap<>();
				users.put("Dave", dave);
				users.put("Jane", jane);
				users.put("Peter", peter);
						
				return users;
			
			}
			
			public static Map<Category, Map<Question, Map<String, Integer>>> createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero() {
				Category movies = new Category(1, "Movies");
				Category sports = new Category(2, "Sports");
				Category travel = new Category(3, "Travel");
				
				//Set up answer objects ready to insert into QuestionWithOptionsImplconstructor
				//Weight not set at this point because at first the answer objects will be added to question class as possible answers.
				//Weight selected at runtime by user.
				var horror = new AnswerWeightedImpl("Horror");
				var action =  new AnswerWeightedImpl("Action"); 
				var romance = new AnswerWeightedImpl("Romance");
				
				List<Answer> movieAnswerOptions = Arrays.asList(horror, action, romance);

				var basketball = new AnswerWeightedImpl("Basketball");
				var football = new AnswerWeightedImpl("Football");
				var swimming = new AnswerWeightedImpl("Swimming");
					
				List<Answer> sportsAnswerOptions = Arrays.asList(basketball, football, swimming);
				
				var hiking = 	new AnswerWeightedImpl("Hiking");
				var sightseeing =  new AnswerWeightedImpl("Sightseeing");
				var camping =  new AnswerWeightedImpl("Camping");
		
				List<Answer> travelAnswerOptions = Arrays.asList(hiking, sightseeing, camping);
				
				//Set up questions objects ready to be inserted into SubmitAnswer constructors
				var questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
						, movies, movieAnswerOptions);
				var questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
						, sports, sportsAnswerOptions);
				var questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
						, travel, travelAnswerOptions);
				
				//Prepopulate map
				Map<String, Integer> presetMovieAnswers = new HashMap<>();
				presetMovieAnswers.put("Horror", 0);
				presetMovieAnswers.put("Action", 0);
				presetMovieAnswers.put("Romance", 0);
				
				Map<Question, Map<String, Integer>> presetQuestionsAndAnswers = new HashMap<>();
				presetQuestionsAndAnswers.put(questionMovies, presetMovieAnswers);

				Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero = new HashMap<>();
				prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero.put(movies, presetQuestionsAndAnswers);
				
				return prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero;
				
			}
			
			public static void main(String[] args) {
				//Fetch users, who have already answered various matching questions
				Map<String, User> users = init();
				User dave = users.get("Dave");
				User jane = users.get("Jane");
				User peter = users.get("Peter");
		
				//Init Matcher and setSearchingUser
				Matcher matcher = new Matcher();
				matcher.setSearchingUser(dave);
				
				//Prepare users list and prepolutaed map. Both are needed for the matching algorithm
				List<User> usersForTesting = new ArrayList<>(users.values());
				Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
				
				//This calculates the match scores. It compares searchingUser to the users in usersForTesting
				try {
					matcher.updateAllMatches(usersForTesting, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
							,new Matcher().new ConvertToPercent(), a -> a.booleanValue() == true? 100 : 0 );
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//The match scores can be grouped in the following ways:
				LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
				Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
				Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = matcher.getTotalMatchPercentageByUserForEveryCategory();
				
				System.out.println("TOTAL MATCH PERCENTAGES BY USER:");
				System.out.println("-----------------------------------------------");
				totalMatchPercentagesByUser.entrySet().stream().forEach( System.out::println);
		
				System.out.println("\n\nTOTAL MATCH PERCENTAGES BY CATEGORY FOR EVERY USER:"); 
				System.out.println("-----------------------------------------------");
				 for (Map.Entry<Category, LinkedHashMap<User, Integer>> map : totalMatchPercentagesByCategoryForEveryUser.entrySet()) {
						System.out.println("Category: " + map.getKey());
						for (Map.Entry<User, Integer> map2 : ((Map<User, Integer>) map.getValue()).entrySet()) {
							System.out.println("User: " + map2.getKey() + " " + map2.getValue());
						}
						System.out.println();
				 }	
				 	 	  
				 System.out.println("\n\nTOTAL MATCH PERCENTAGE BY USER FOR EVERY CATEGORY:"); 
				 System.out.println("-----------------------------------------------");
				 for (Map.Entry<User, LinkedHashMap<Category, Integer>> map :  totalMatchPercentageByUserForEveryCategory.entrySet()) {
						System.out.println("User: " + map.getKey());
						for (Map.Entry<Category, Integer> map2 : map.getValue().entrySet()) {
							System.out.println("Category: " + map2.getKey() + " " + map2.getValue());
						}
						System.out.println();				
				 }
				 
	
			}
			
			
	
	
			
			
			

}
