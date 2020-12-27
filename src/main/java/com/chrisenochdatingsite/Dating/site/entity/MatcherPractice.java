package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswer;


public class MatcherPractice {
	
	//private static Set<SubmittedAnswer> init() 
			private static Map<String, User> init() {
				
				Category movies = new Category("Movies");
				Category sports = new Category("Sports");
				Category travel = new Category("Travel");
				
				//Set up answer objects ready to insert into QuestionWithObjects object constructor
				//Weight not set because at this point because at first the answer objects will be added to question class as possible answers.
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
				var submittedAnsDaveSports1 = new SubmittedAnswerMultiImpl(questionSports, dave, sportsChoiceDave1, sportsChoiceDave2, sportsChoiceDave3);
				var submittedAnsDaveTravel1 = new SubmittedAnswerMultiImpl(questionTravel, dave, travelStyleChoiceDave1, travelStyleChoiceDave2,travelStyleChoiceDave3);
				
				List<SubmittedAnswer> daveAns = Arrays.asList(submittedAnsDaveMovies1, submittedAnsDaveSports1,submittedAnsDaveTravel1 );
				
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
				var submittedAnsPeterSports1 = new SubmittedAnswerMultiImpl(questionSports, peter,sportsChoicePeter1, sportsChoicePeter2, sportsChoicePeter3);
				var submittedAnsPeterTravel1 = new SubmittedAnswerMultiImpl(questionTravel, peter, travelStyleChoicePeter1, travelStyleChoicePeter2, travelStyleChoicePeter3);
				
				List<SubmittedAnswer> peterAns = Arrays.asList(submittedAnsPeterMovies1, submittedAnsPeterSports1, submittedAnsPeterTravel1);				
				
				peter.setSubmittedAnswers(peterAns);
				
				Map<String, User> users = new HashMap<>();
				users.put("Dave", dave);
				users.put("Jane", jane);
				users.put("Peter", peter);
				
				
				//List<User> users = Arrays.asList(dave, jane, peter);
				
//				Set<SubmittedAnswer> submittedAnswers = new HashSet<>();
//				//submittedAnswers.add(submittedAnsDaveMovies1);
//				submittedAnswers.add(submittedAnsDaveSports1);
//				submittedAnswers.add(submittedAnsDaveTravel1);
//				//submittedAnswers.add(submittedAnsJaneMovies1);
//				submittedAnswers.add(submittedAnsJaneSports1);
//				submittedAnswers.add(submittedAnsJaneTravel1);
//				//submittedAnswers.add(submittedAnsPeterMovies1);
//				submittedAnswers.add(submittedAnsPeterSports1);
//				submittedAnswers.add(submittedAnsPeterTravel1);
				
				return users;
			
			}
			
			public static Map<Category, Map<Question, Map<String, Integer>>> createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero() {
				Category movies = new Category("Movies");
				Category sports = new Category("Sports");
				Category travel = new Category("Travel");
				
				//Set up answer objects ready to insert into QuestionWithObjects object constructor
				//Weight not set because at this point because at first the answer objects will be added to question class as possible answers.
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
				//preSetMovieAnswers
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
				
				//calculateMatch6(init());
				Map<String, User> users = init();
				User dave = users.get("Dave");
				User jane = users.get("Jane");
				User peter = users.get("Peter");
				
				long startTime = System.currentTimeMillis();
				
				Matcher matcher = new Matcher();
				matcher.setSearchingUser(peter);
				
				List<User> usersForTesting = new ArrayList<>(users.values());
				
				
				 Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
				
				
				try {
					matcher.updateAllMatches(usersForTesting, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
							,new Matcher().new ConvertToPercent(), a -> a.booleanValue() == true? 100 : 0 );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
				Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
				Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = matcher.getTotalMatchPercentageByUserForEveryCategory();

				totalMatchPercentagesByUser.entrySet().stream().forEach(System.out::println);
				
				 System.out.println();
				 
				 for (Map.Entry map : totalMatchPercentagesByCategoryForEveryUser.entrySet()) {
						System.out.println("Category: " + map.getKey());
						for (Map.Entry map2 : ((Map<User, Integer>) map.getValue()).entrySet()) {
							System.out.println("User: " + map2.getKey() + " " + map2.getValue());
						}
						
				 }
				 
				 System.out.println();
				 
				 for (Map.Entry map3 :  totalMatchPercentageByUserForEveryCategory.entrySet()) {
						System.out.println("User: " + map3.getKey());
						for (Map.Entry map4 : ((Map<User, Integer>) map3.getValue()).entrySet()) {
							System.out.println("Category: " + map4.getKey() + " " + map4.getValue());
						}
						
				 }
				 
				 long finishTime = System.currentTimeMillis();
				 System.out.println("Run time: " + (finishTime - startTime)/1000.0);
				
				 
				 System.out.println("Start matchPercentageByCategoryAndAnswer");
				Map<Category, Map<Question, Map<String, Integer>>> matchesDave = null;
				Map<Category, Map<Question, Map<String, Integer>>> matchesJane = null;
				try {
					matchesDave = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), dave
							,prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, new Matcher().new ConvertToPercent()
							, a -> a.booleanValue() == true? 100 : 0 );
//					matchesJane = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), jane
//							,prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero,  new Matcher().new ConvertToPercent()
//							, a -> a.booleanValue() == true? 100 : 0 );
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//e.getMessage();
				} 
				
				System.out.println("Print the contents");
				
				
				for (Map.Entry map1 : matchesDave.entrySet()) {
					System.out.println("Category: " + map1.getKey());
					for (Map.Entry map2 : ((Map<String, Answer>) map1.getValue()).entrySet()) {
						System.out.println("Question: " + map2.getKey());
						for (Map.Entry map3 : ((Map<String, Answer>) map2.getValue()).entrySet()) {
							System.out.println("AnswerTxt: " + map3.getKey() + " Weight: " + map3.getValue());
						}
						System.out.println("------------------------------");
					}		

				}
//				
//				System.out.println("------------------------------");
//				LinkedHashMap<Category, Integer> percentagesByCategoryDave = matcher.totalMatchPercentageByCategory(matchesDave);
//				LinkedHashMap<Category, Integer> percentagesByCategoryJane = matcher.totalMatchPercentageByCategory(matchesJane);
//				percentagesByCategoryDave.entrySet().stream().forEach(System.out::println);
//				
//				System.out.println("------------------------------");
//				
//				LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
//				//TreeMap<Integer, User> totalMatchPercentagesByUser = new TreeMap<>(Comparator.comparing(Integer::intValue()).reversed());
//				//TreeMap<User, Integer> totalMatchPercentagesByUser = new TreeMap<>(Comparator.comparing((User a) -> a.getFirstName()).reversed());
//				matcher.updateTotalMatchPercentagesByUser(dave, totalMatchPercentagesByUser, percentagesByCategoryDave);
//				matcher.updateTotalMatchPercentagesByUser(jane, totalMatchPercentagesByUser, percentagesByCategoryJane);
//				
//				//totalMatchPercentagesByUser.entrySet().stream().forEach(System.out::println);
//				
//				//LinkedHashMap<User, Integer> totalMatchPercentagesByUserDescending = sortByPercentageDescending (totalMatchPercentagesByUser, new Matcher().new ValueComparator());
//				
//				for (Map.Entry map : totalMatchPercentagesByUser.entrySet()) {
//					System.out.println("Total match Percentages By User: " + map.getKey() + " " + map.getValue());
//				}
//				
//				System.out.println("TotalMatchPercentagesByUser set?" + matcher.getTotalMatchPercentagesByUser());
//				
//				//matcher.setTotalMatchPercentagesByUser(totalMatchPercentagesByUser);
//				
////				private void updateTotalMatchPercentagesByUser(User userToAdd, Map<User, Integer> totalMatchPercentagesByUser
////						, Map<Category, Integer> matchPercentageByCategory){	
//				
//				Map<Category, LinkedHashMap<User, Integer>> totalMatchPercentagesByCategoryAndUser = matcher.getTotalMatchPercentagesByCategoryAndUser();
//				matcher.updateTotalMatchPercentagesByCategoryAndUser(dave, percentagesByCategoryDave, totalMatchPercentagesByCategoryAndUser);
//				matcher.updateTotalMatchPercentagesByCategoryAndUser(jane, percentagesByCategoryJane, totalMatchPercentagesByCategoryAndUser);
//				
//				for (Map.Entry map : totalMatchPercentagesByCategoryAndUser.entrySet()) {
//					System.out.println("Category: " + map.getKey());
//					for (Map.Entry map2 : ((Map<User, Integer>) map.getValue()).entrySet()) {
//						System.out.println("User: " + map2.getKey() + " " + map2.getValue());
//					}
//					
//				}
//				
//				System.out.println("TotalMatchPercentagesByCategoryAndUser set?" + matcher.getTotalMatchPercentagesByCategoryAndUser());
//				
			}
			
			
	
	
			
			
			

}
