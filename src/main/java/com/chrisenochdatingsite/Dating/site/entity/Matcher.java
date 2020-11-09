package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;


public class Matcher {
		private Set<SubmittedAnswer> submittedAnswers;	
		
		private void init() {
			
			Category movies = new Category("Movies");
			Category sports = new Category("Sports");
			Category travel = new Category("Travel");
			
			Set<Answer> movieAnswerOptions = new HashSet<>(
					Arrays.asList(
					new AnswerWeightedImpl("Horror", movies)
					, new AnswerWeightedImpl("Action", movies)
					, new AnswerWeightedImpl("Romance", movies)
					));
			
			Set<Answer> sportsAnswerOptions = new HashSet<>(
					Arrays.asList(
					new AnswerWeightedImpl("Basketball", sports)
					, new AnswerWeightedImpl("Football", sports)
					, new AnswerWeightedImpl("Swimming", sports)
					));
			
			Set<Answer> travelAnswerOptions = new HashSet<>(
					Arrays.asList(
					new AnswerWeightedImpl("Hiking", travel)
					, new AnswerWeightedImpl("Sightseeing", travel)
					, new AnswerWeightedImpl("Camping", travel)
					));
			
			var questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
					, movieAnswerOptions);
			var questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
					, sportsAnswerOptions);
			var questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
					, travelAnswerOptions);
			
			User dave = new User("Dave", "Smith", "dave@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
			User jane = new User("Jane", "Jones", "jane@yahoo.com", LocalDate.of(1984,  10,  24), Sex.FEMALE);
			User peter = new User("Peter", "Hanks", "peter@yahoo.com", LocalDate.of(1982,  8,  22), Sex.MALE);
			
			//User1 
			//Problem, need to be able to retrieve from answerOptions
			//In which case, needs to be a map
			var movieChoice = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE, movies);
			var sportsChoice = new AnswerWeightedImpl("Football", AnswerWeight.THREE, sports);
			var travelStyleChoice= new AnswerWeightedImpl("Camping", AnswerWeight.TWO, travel);
			
			var submittedAns1 = new SubmittedAnswerSingleImpl(questionMovies,horror, dave);
			var submittedAns2 = new SubmittedAnswerSingleImpl(questionSports,football, dave);
			var submittedAns3 = new SubmittedAnswerSingleImpl(questionTravel,camping, dave);
			
			//User 2
			var movieChoice2r = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE, movies);
			var sportsChoice2 = new AnswerWeightedImpl("Football", AnswerWeight.THREE, sports);
			var travelStyleChoice2 = new AnswerWeightedImpl("Camping", AnswerWeight.TWO, travel);
			
			var submittedAns1 = new SubmittedAnswerSingleImpl(questionMovies,horror, dave);
			var submittedAns2 = new SubmittedAnswerSingleImpl(questionSports,football, dave);
			var submittedAns3 = new SubmittedAnswerSingleImpl(questionTravel,camping, dave);
 			
			
			
		}
		
		//Arguments List<SubmittedAnswer> userAnswer, List<User<HashSet<SubmittedAnswer>> otherPeoplesAnswers>, 
		//First pair each SubmittedAnswer with each SubmittedAnswerList //Would TreeMap be useful here? make method faster? //Hashmap quicker
			//Find question element in Hashset using contains. if true add to collection
			//NEED FAST SEARCH
			//End up with map questionid, SubmittedAnswer userAnswer, List<SubmittedAnswer>>
			//NEED FAST ITERATION, FAST LOOKUP
		
		//End up with matching result for each category
		//Separate map for each category
		//End up with total matching percentage
		//If use partion.... end up with deep arrays and take ages to retrieve data?
			//Do by means of partition.
			//Do by means of smaller methods
		
		
		//Some people may have answered more questions than others. How to account for this?
		
		//Separate/Partition by category
			//Use map function for if statements. Extract into method reference
			//If instanceof AnswerWeightedimpl
				//Create map, <User, Double percentage>
					//Testing, use double with margin for error. e.g. hamcrest Closeto method.
				
				//calculate matches method (could be added as a filter? via method reference?)BiFunction?
					//Find difference between each score, if diff 0 100%, if difference 5-0% match
					//find weight of Person A, find weight of Person B, map subtract one from other, 
					//map convert into percentage double
					//make sure ends up as stream, so can plug in the method to larger method?
				//could be adding to existing map? (Pearl: Only calculate newly-answered questions/uncached results)
		
			//If instanceof SubmittedAnswerSingle
					//Need to add to same map as previous one. if same answer 100%, if diff answer 0% this matching algorithm seems to harsh. Maybe user doesn't care about other person beig difefrent.
					//Create new map of answers. <User, Double percentage>
					//Merge this map with previous map, combination part calculates new percentage based on the two percentages for each map
					//Separate methods for? /Take into account if multianswer or singleanswer. Maybe SubmittedAnswerMulti should be subclass of SubmittedAnswerSingle? Or maybe they should implement a common inetrface. Abstract class Submitetd Answer
					
			//If instance of SubmittedAnswerMulti
					//For which questions are we going to have multianswers? looking for relationship status, if one selects one and other selects all, if anymatch then should be calculated as 100% compatible
					//100% match if any coincide. if user chooses any/all, then 100% match
		
				//merge the 3 maps into one. combine by finding the mean percentage. this part could be doe by multithreading and custom combiner?
				
				
		
		
}		
