package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;


public class Matcher {
		private Set<SubmittedAnswer> submittedAnswers;	
		
		private void init() {
			
			Category movies = new Category("Movies");
			Category sports = new Category("Sports");
			Category travel = new Category("Travel");
			
			//Set up answer objects ready to insert into QuestionWithObjects object constructor
			//Weight not set because at this point because at first the answer objects will be added to question class as possible answers.
			//Weight selected at runtime by user.
			var horror = new AnswerWeightedImpl("Horror", movies);
			var action =  new AnswerWeightedImpl("Action", movies);
			var romance = new AnswerWeightedImpl("Romance", movies);
			
			Map<String, Answer> movieAnswerOptions = new HashMap<>();
			movieAnswerOptions.put(horror.getAnswerText(), horror);
			movieAnswerOptions.put(action.getAnswerText(), action);
			movieAnswerOptions.put(romance.getAnswerText(), romance);  //Good candidate for test.

			var basketball = new AnswerWeightedImpl("Basketball", sports);
			var football = new AnswerWeightedImpl("Football", sports);
			var swimming = new AnswerWeightedImpl("Swimming", sports);
				
			Map<String, Answer> sportsAnswerOptions = new HashMap<>();
			sportsAnswerOptions.put(basketball.getAnswerText(), basketball);
			sportsAnswerOptions.put(football.getAnswerText(), football);
			sportsAnswerOptions.put(swimming.getAnswerText(), swimming);
			
			var hiking = 	new AnswerWeightedImpl("Hiking", travel);
			var sightseeing =  new AnswerWeightedImpl("Sightseeing", travel);
			var camping =  new AnswerWeightedImpl("Camping", travel);
	
			Map<String, Answer> travelAnswerOptions = new HashMap<>();
			travelAnswerOptions.put(hiking.getAnswerText(), hiking);
			travelAnswerOptions.put(sightseeing.getAnswerText(), sightseeing);
			travelAnswerOptions.put(camping.getAnswerText(), camping);
			
			//Set up questions objects ready to be inserted into SubmitAnswer constructors
			var questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
					, movieAnswerOptions);
			var questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
					, sportsAnswerOptions);
			var questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
					, travelAnswerOptions);
			
			User dave = new User("Dave", "Smith", "dave@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
			User jane = new User("Jane", "Jones", "jane@yahoo.com", LocalDate.of(1984,  10,  24), Sex.FEMALE);
			User peter = new User("Peter", "Hanks", "peter@yahoo.com", LocalDate.of(1982,  8,  22), Sex.MALE);
			
			//USER 1
			//Create answer objects with weight
			//Set selected answers for questions objects. Set here to reflect real-life flow of control.

			var movieChoiceDave1 = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE, movies);
			var movieChoiceDave2 = new AnswerWeightedImpl("Action", AnswerWeight.FOUR, movies);
			var sportsChoiceDave1 = new AnswerWeightedImpl("Football", AnswerWeight.THREE, sports);
			var travelStyleChoiceDave1= new AnswerWeightedImpl("Camping", AnswerWeight.TWO, travel);
			
			//Problem: cannot add more than one answer 
			questionMovies.setSelectedAnswer(movieChoiceDave1);
			questionSports.setSelectedAnswer(sportsChoiceDave1);
			questionTravel.setSelectedAnswer(travelStyleChoiceDave1);
			
			var submittedAnsDaveMovies1 = new SubmittedAnswerSingleImpl(questionMovies, dave);
			var submittedAnsDaveSports1 = new SubmittedAnswerSingleImpl(questionSports, dave);
			var submittedAnsDaveTravel1 = new SubmittedAnswerSingleImpl(questionTravel, dave);
			
			//USER 2
			var movieChoiceJane1 = new AnswerWeightedImpl("Horror", AnswerWeight.SIX, movies); //Improve code: Could mistakingly add a string that does not exist as answer option.
			var sportsChoiceJane1 = new AnswerWeightedImpl("Basketball", AnswerWeight.ZERO, sports);
			var travelStyleChoiceJane1 = new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE, travel);
			
			questionMovies.setSelectedAnswer(movieChoiceJane1);
			questionSports.setSelectedAnswer(sportsChoiceJane1);
			questionTravel.setSelectedAnswer(travelStyleChoiceJane1);
			
			var submittedAnsJaneMovies1 = new SubmittedAnswerSingleImpl(questionMovies, jane);
			var submittedAnsJaneSports1 = new SubmittedAnswerSingleImpl(questionSports, jane);
			var submittedAnsJaneTravel1 = new SubmittedAnswerSingleImpl(questionTravel, jane);
 			
			//USER 3
			var movieChoicePeter1 = new AnswerWeightedImpl("Horror", AnswerWeight.ZERO, movies); //Improve code: Could mistakingly add a string that does not exist as answer option.
			var sportsChoicePeter1 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE, sports);
			var travelStyleChoicePeter1 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.SIX, travel);
			
			questionMovies.setSelectedAnswer(movieChoicePeter1);
			questionSports.setSelectedAnswer(sportsChoicePeter1);
			questionTravel.setSelectedAnswer(travelStyleChoicePeter1);
			
			var submittedAnsPeterMovies1 = new SubmittedAnswerSingleImpl(questionMovies, peter);
			var submittedAnsPeterSports1 = new SubmittedAnswerSingleImpl(questionSports, peter);
			var submittedAnsPeterTravelStyle1 = new SubmittedAnswerSingleImpl(questionTravel, peter);
			
		}
		
		//Thoughts. If i use answerkey as key for hashmap.
		//then when I check that answerkey exisst in list of answers in questions class, can 
		
		//Arguments List<SubmittedAnswer> userAnswer, <SubmittedAnswer> otherPeoplesAnswers>, 
		//First pair each SubmittedAnswer with each SubmittedAnswerList //Would TreeMap be useful here? make method faster? //Hashmap quicker
			//Find question element in Hashset using contains. if true add to collection
			//NEED FAST SEARCH
			//End up with map questionid, SubmittedAnswer userAnswer, List<SubmittedAnswer>>
			//NEED FAST ITERATION, FAST LOOKUP 
		
		//Check question and answer coincide  //Do I need the question? If just matching, do not need.If going to display, need.
		//What about if user has selected multiple answers? In that case, need to be able search by question id?
		//Need Question for multiselect answers. But will have different answerkeys?, so need by questionid
		//In Answer class, setQuestion, then can get questionTextString from question if necessary and then don't need to worry about question and answer arguments not matching.
		
		
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
					//Check tos ee if weight is set, if not, skip/continue
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
				
				
		
		
		//
//		Set<Answer> sportsAnswerOptions = new HashSet<>(
//				Arrays.asList(
//				new AnswerWeightedImpl("Basketball", sports)
//				, new AnswerWeightedImpl("Football", sports)
//				, new AnswerWeightedImpl("Swimming", sports)
//				));
		
}		
