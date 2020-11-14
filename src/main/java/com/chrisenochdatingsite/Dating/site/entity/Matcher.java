package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswer;


public class Matcher {
		private Set<SubmittedAnswer> submittedAnswers;	
		
		//private static Set<SubmittedAnswer> init()
		private static List<User> init() {
			
			Category movies = new Category("Movies");
			Category sports = new Category("Sports");
			Category travel = new Category("Travel");
			
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
			
			User dave = new User("Dave", "Smith", "dave@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
			User jane = new User("Jane", "Jones", "jane@yahoo.com", LocalDate.of(1984,  10,  24), Sex.FEMALE);
			User peter = new User("Peter", "Hanks", "peter@yahoo.com", LocalDate.of(1982,  8,  22), Sex.MALE);
			
			//USER 1
			//Create answer objects with weight
			//Set selected answers for questions objects. Set here to reflect real-life flow of control.

			var movieChoiceDave1 = new AnswerWeightedImpl("Horror", AnswerWeight.FIVE);
			var movieChoiceDave2 = new AnswerWeightedImpl("Action", AnswerWeight.FOUR);
			var movieChoiceDave3 = new AnswerWeightedImpl("Romance", AnswerWeight.THREE);
			var sportsChoiceDave1 = new AnswerWeightedImpl("Football", AnswerWeight.THREE);
			var sportsChoiceDave2 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
			var sportsChoiceDave3 = new AnswerWeightedImpl("Basketball", AnswerWeight.SIX);
			var travelStyleChoiceDave1= new AnswerWeightedImpl("Camping", AnswerWeight.TWO);
			var travelStyleChoiceDave2= new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE);
			var travelStyleChoiceDave3= new AnswerWeightedImpl("Sightseeing", AnswerWeight.ONE);
			
			var submittedAnsDaveMovies1 = new SubmittedAnswerMultiImpl(questionMovies, dave, movieChoiceDave1, movieChoiceDave2, movieChoiceDave3);
			var submittedAnsDaveSports1 = new SubmittedAnswerMultiImpl(questionSports, dave, sportsChoiceDave1, sportsChoiceDave2, sportsChoiceDave3);
			var submittedAnsDaveTravel1 = new SubmittedAnswerMultiImpl(questionTravel, dave, travelStyleChoiceDave1, travelStyleChoiceDave2, travelStyleChoiceDave3);
			
			Map<String, SubmittedAnswer> daveAns = new HashMap<>();
			daveAns.put(submittedAnsDaveMovies1.getQuestion().getQuestionText(), submittedAnsDaveMovies1);
			daveAns.put(submittedAnsDaveSports1.getQuestion().getQuestionText(), submittedAnsDaveSports1);
			daveAns.put(submittedAnsDaveTravel1.getQuestion().getQuestionText(), submittedAnsDaveTravel1);
			
			dave.setSubmittedAnswers(daveAns);
			
			//USER 2
			var movieChoiceJane1 = new AnswerWeightedImpl("Horror", AnswerWeight.SIX); //Improve code: Could mistakingly add a string that does not exist as answer option.
			var movieChoiceJane2 = new AnswerWeightedImpl("Action", AnswerWeight.FIVE);
			var movieChoiceJane3 = new AnswerWeightedImpl("Romance", AnswerWeight.FOUR);
			var sportsChoiceJane1 = new AnswerWeightedImpl("Basketball", AnswerWeight.ZERO);
			var sportsChoiceJane2 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
			var sportsChoiceJane3 = new AnswerWeightedImpl("Football", AnswerWeight.SIX);	
			var travelStyleChoiceJane1 = new AnswerWeightedImpl("Hiking", AnswerWeight.FIVE);
			var travelStyleChoiceJane2 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.FIVE);
			var travelStyleChoiceJane3 = new AnswerWeightedImpl("Camping", AnswerWeight.FIVE);

			var submittedAnsJaneMovies1 = new SubmittedAnswerMultiImpl(questionMovies, jane, movieChoiceJane1, movieChoiceJane2, movieChoiceJane3);
			var submittedAnsJaneSports1 = new SubmittedAnswerMultiImpl(questionSports, jane, sportsChoiceJane1,sportsChoiceJane2, sportsChoiceJane3);
			var submittedAnsJaneTravel1 = new SubmittedAnswerMultiImpl(questionTravel, jane, travelStyleChoiceJane1, travelStyleChoiceJane2, travelStyleChoiceJane3);
 			
			Map<String, SubmittedAnswer> janeAns = new HashMap<>();
			janeAns.put(submittedAnsJaneMovies1.getQuestion().getQuestionText(), submittedAnsJaneMovies1);
			janeAns.put(submittedAnsJaneSports1.getQuestion().getQuestionText(), submittedAnsJaneSports1);
			janeAns.put(submittedAnsJaneTravel1.getQuestion().getQuestionText(), submittedAnsJaneTravel1 );
			
			jane.setSubmittedAnswers(janeAns); 
			
			//USER 3
			var movieChoicePeter1 = new AnswerWeightedImpl("Horror", AnswerWeight.ZERO); //Improve code: Could mistakingly add a string that does not exist as answer option.
			var movieChoicePeter2 = new AnswerWeightedImpl("Romance", AnswerWeight.ZERO);
			var movieChoicePeter3 = new AnswerWeightedImpl("Action", AnswerWeight.SIX);
	
			var sportsChoicePeter1 = new AnswerWeightedImpl("Swimming", AnswerWeight.ONE);
			var sportsChoicePeter2 = new AnswerWeightedImpl("Football", AnswerWeight.FIVE);
			var sportsChoicePeter3 = new AnswerWeightedImpl("Basketball", AnswerWeight.FOUR);
			
			var travelStyleChoicePeter1 = new AnswerWeightedImpl("Sightseeing", AnswerWeight.SIX);
			var travelStyleChoicePeter2 = new AnswerWeightedImpl("Camping", AnswerWeight.SIX);
			var travelStyleChoicePeter3 = new AnswerWeightedImpl("Hiking", AnswerWeight.THREE);
			
			var submittedAnsPeterMovies1 = new SubmittedAnswerMultiImpl(questionMovies, peter,movieChoicePeter1, movieChoicePeter2, movieChoicePeter3);
			var submittedAnsPeterSports1 = new SubmittedAnswerMultiImpl(questionSports, peter,sportsChoicePeter1, sportsChoicePeter2, sportsChoicePeter3);
			var submittedAnsPeterTravel1 = new SubmittedAnswerMultiImpl(questionTravel, peter, travelStyleChoicePeter1, travelStyleChoicePeter2, travelStyleChoicePeter3);
			
			Map<String, SubmittedAnswer> peterAns = new HashMap<>();
			peterAns.put(submittedAnsPeterMovies1.getQuestion().getQuestionText(), submittedAnsPeterMovies1);
			peterAns.put(submittedAnsPeterSports1 .getQuestion().getQuestionText(), submittedAnsPeterSports1);
			peterAns.put(submittedAnsPeterTravel1.getQuestion().getQuestionText(), submittedAnsPeterTravel1);
			
			peter.setSubmittedAnswers(peterAns);
			
			List<User> users = Arrays.asList(dave, jane, peter);
			
			Set<SubmittedAnswer> submittedAnswers = new HashSet<>();
			submittedAnswers.add(submittedAnsDaveMovies1);
			submittedAnswers.add(submittedAnsDaveSports1);
			submittedAnswers.add(submittedAnsDaveTravel1);
			submittedAnswers.add(submittedAnsJaneMovies1);
			submittedAnswers.add(submittedAnsJaneSports1);
			submittedAnswers.add(submittedAnsJaneTravel1);
			submittedAnswers.add(submittedAnsPeterMovies1);
			submittedAnswers.add(submittedAnsPeterSports1);
			submittedAnswers.add(submittedAnsPeterTravel1);
			
			return users;
		
		}
		

		

		//IDEAS
		//Match formulas should be lambdas so can always change easily?
		//Thoughts. If i use answerkey as key for hashmap.
		//then when I check that answerkey exist in list of answers in questions class, can 
		
		//Category may be better being the final operation as it is a terminal operation.
		//Arguments List<SubmittedAnswer> userAnswer, <SubmittedAnswer> otherPeoplesAnswers>, 
		//First pair each SubmittedAnswer with each SubmittedAnswerList //Would TreeMap be useful here? make method faster? //Hashmap quicker
			//Find question element in Hashset using contains. if true add to collection
			//NEED FAST SEARCH
			//End up with map questionid, SubmittedAnswer userAnswer, List<SubmittedAnswer>>
			//NEED FAST ITERATION, FAST LOOKUP 
		//Check question and answer coincide  //Do I need the question? If just matching, do not need.If going to display, need.
		//What about if user has selected multiple answers? In that case, need to be able search by question id?
		//Need Question for multiselect answers. But will have different answerkeys?, so need by questionid
		//End up with matching result for each category
		//Separate map for each category
		//End up with total matching percentage
		//Do by means of smaller methods?
		//Some people may have answered more questions than others. How to account for this?
		
		
		
		//SIMPLE VERISON: One type of question
		
		//Map <User, Double percentage> for each category
		//First group by category and then groupBy User
		//Map <Category, Map <User, Double>> matchPercentageByCategory
		
		 //This compares one user with one other user.
		//Later this method will be fed into a another in order to calculate matches with all users.
		//This is the one I will want to use parallel programming for as the user list could be in the millions.
		// usersList.parallelstream().map(a-> Matchers.matchPercentageByCategory(a)  ..., groupBy category
		//Map<Category, Map<User,Map<Question, AnswerWeight>>>
		//First assume that all the same type of answer?
		//Real return type: Map<Category, Map<Question, AnswerWeight>> 
		/*
		 * 0. 
		 * 1. Get SubmittedAnswers from SearchingUser (will need to add this to User class)
		 * 2. Get SubmittedAnswers from Comparing User;
		 * 3. Loop through. Check  SUSer question with C user question.
		 * 4. If no match, continue
		 * 5. Get Weight from both Users and store in 2 diff vars.
		 * 6. Subtract diff, making sure no minus value (absoluteValue?)
		 * In future, calculate match percentage here depending on type of answer. 
		 * This must be separate method / the place where a method ref can be inserted. - See Java 8 book on generalisign methods.
		 * Switch/If conditions here. 
		 * 7. Store in appropriate place of Map.
		 * 8. Create separate method to calculate match percentage.
		 * 
		 * Do iteratively first and then change to streams.

		 */
		
		public static void main(String[] args) {
			//calculateMatch6(init());
			List<User> usersForTesting = init();
			User dave = usersForTesting.get(0);
			User jane = usersForTesting.get(1);
			User peter = usersForTesting.get(2);
			
			Map<String, SubmittedAnswer> daveMap = dave.getSubmittedAnswers();
			SubmittedAnswer ans = daveMap.get("Please indicate how much you like the following movie genres.");
			SubmittedAnswerMultiImpl ansMulti = (SubmittedAnswerMultiImpl) ans;
			
			//QuestionText , answer
			Map<String, Answer> map =  ansMulti.getSelectedAnswers();
			
		


			Map<Category, Map<Question, Integer>> matches = matchPercentageByCategory(peter, dave);

			
			for (Map.Entry pair : matches.entrySet()) {
				System.out.println(pair.getKey());
				for (Map.Entry pair2 : ((Map<String, Answer>) pair.getValue()).entrySet()) {
					System.out.println("Key: " + pair2.getKey() + " Value: " + pair2.getValue());
				}
				
				System.out.println("**************************");
			}
			
			
			 Map<Category, Map<Question,Map<AnswerWeightedImpl,Integer>>>testMap; //May be too complicated

		}
	
		//Return type is temporary. This will need to be changed to returning: Map<Category, Map<Question, Double>> matchPercentagesByCategory
		private static Map<Category, Map<Question, Integer>> matchPercentageByCategory(User searchingUser, User comparedUser){
			//Improve, maybe map already exists in database. Get from there, use caching and only calculate changed values?
			//matchPercentagesByCategory gets calculated at end along with other maps. Then all get merged into one map, which gets returned from the method?
			//method which calculates match scores should be one f functional inetrface, so can repolace matchign algorithm easily
			Map<Category, Map<Question, Double>> matchPercentagesByCategory;
			Map<Category, Map<Question, Integer>> matchWeightsByCategory = new HashMap<>(); //Integer = diffInWeight
			
			Map<String, SubmittedAnswer> searchingUserAnswers = searchingUser.getSubmittedAnswers();
			Map<String, SubmittedAnswer> comparedUserAnswers = comparedUser.getSubmittedAnswers();
			
			//String is questionText
			for (Map.Entry<String, SubmittedAnswer> pair : searchingUserAnswers.entrySet()) {
				SubmittedAnswer searchingUserAns = pair.getValue();
				
				if (searchingUserAns instanceof SubmittedAnswerMultiImpl) {
					SubmittedAnswerMultiImpl searchingUserAnsMultiImpl = (SubmittedAnswerMultiImpl) searchingUserAns;
					
					String searchingUserQuestionText = searchingUserAns.getQuestion().getQuestionText();		
					SubmittedAnswer comparedUserAns = comparedUserAnswers.get(searchingUserQuestionText);
					
					SubmittedAnswerMultiImpl comparedUserAnsMultiImpl = (SubmittedAnswerMultiImpl) comparedUserAns;
					
					//do null check
					if (comparedUserAns == null) {
						continue; //Not all users will have submitted the same answers. If no matching SubmittedAnswer, go on to the next one.
					}
		
					Map<String, Answer> searchingUserSelectedAnswers = searchingUserAnsMultiImpl.getSelectedAnswers();
					Map<String, Answer> comparedUserSelectedAnswers = comparedUserAnsMultiImpl.getSelectedAnswers();
					
					//Loop through and compare scores and add to right category.
					AnswerWeightedImpl searchingUserAnsWeighted = null;
					for (Map.Entry<String, Answer> map : searchingUserSelectedAnswers.entrySet()) {
						Answer ans = map.getValue();
						if (ans instanceof AnswerWeightedImpl) {
							searchingUserAnsWeighted = (AnswerWeightedImpl) ans;
							
							AnswerWeightedImpl comparedUserAnswerWeighted = (AnswerWeightedImpl) comparedUserSelectedAnswers.get(searchingUserAnsWeighted.getAnswerText());
							int diffInWeight; 
							if (comparedUserAnswerWeighted != null) {
								System.out.println("Suser weight calculated: " + searchingUserAnsWeighted.getAnswerWeight().getWeight());
								System.out.println("Cuser weight calculated: " + comparedUserAnswerWeighted.getAnswerWeight().getWeight());
								
								
								diffInWeight = Math.abs(searchingUserAnsWeighted.getAnswerWeight().getWeight() - comparedUserAnswerWeighted.getAnswerWeight().getWeight());
							System.out.println("diffInWeight calculated: " + diffInWeight);
							} else {
								//throw exception. Do custom exception? / continue loop?
								continue;
							}
							
							//Get category and add score to Map<Category, Map<Question, AnswerWeight>> matchWeightsByCategory;
							Category category = searchingUserAns.getQuestion().getCategory();
							//Improve code: get comparedUser category and only proceed if categories match? Perhaps unnecessary
							
							//Dont overwrite current entries for specified category
							if (matchWeightsByCategory.containsKey(category)) {
								Map<Question, Integer> tempMap = matchWeightsByCategory.get(category);
								tempMap.put(searchingUserAns.getQuestion(), diffInWeight);
								matchWeightsByCategory.put(category, tempMap);	
								
							} else {
								Map<Question, Integer> tempMap = new HashMap<>();
								tempMap.put(searchingUserAns.getQuestion(), diffInWeight);
								matchWeightsByCategory.put(category, tempMap);
							}
							
							
						} else {
							//Do logic for other answer types later
							continue; // THIS MUST BE CHANGED. ONLY ADDED TEMPRARILY WHILE NO OTHER ANSWER TYPES.
						}		
						
					}
					
			
				} else { //If not instanceof SubmittedAnswerMultiImpl		
				}							
			}
			
			return matchWeightsByCategory;
		}
		

		
		private Map<Category,Set<SubmittedAnswer>> testJava8(List<Integer> testList){
			testList.stream().map(a-> a.doubleValue()).collect(Collectors.toList());
			return null;
		}
		
		
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch6(Set<SubmittedAnswer> submittedAnswers){
			Map<Category, Set<SubmittedAnswer>> test = submittedAnswers.stream().collect(Collectors.groupingBy(a-> a.getQuestion()
					.getCategory(), Collectors.toSet()));
			//List<String> test = submittedAnswers.stream().map(a -> a.getQuestion().getCategory()
			
			
			System.out.println("testing with maps start");
			test.forEach((a, b)-> System.out.println(a.getCategory() + " " + b.stream()
			.filter(c->c instanceof SubmittedAnswerMultiImpl).map(c->(SubmittedAnswerMultiImpl)c)
			.map((c)-> {return "\n" + c.getQuestion().getQuestionText() 
					+ "\n" +  c.getUser().getFirstName() + "\n" + c.getSelectedAnswers() + "\n" ;
					
			}
					).collect(Collectors.toList()) + "\n"));
			System.out.println("testing with maps finish");

			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch5(Set<SubmittedAnswer> submittedAnswers){
			Map<Category, List<SubmittedAnswer>> test = submittedAnswers.stream().collect(Collectors.groupingBy(a-> a.getQuestion().getCategory()));
			//List<String> test = submittedAnswers.stream().map(a -> a.getQuestion().getCategory()
			
			
			System.out.println("testing with maps start");
			test.forEach((a, b)-> System.out.println(a.getCategory() + " " + b.stream()
			.filter(c->c instanceof SubmittedAnswerMultiImpl).map(c->(SubmittedAnswerMultiImpl)c)
			.map((c)-> {return "\n" + c.getQuestion().getQuestionText() 
					+ "\n" +  c.getUser().getFirstName() + "\n" + c.getSelectedAnswers() + "\n" ;
					
			}
					).collect(Collectors.toList()) + "\n"));
			System.out.println("testing with maps finish");

			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch4(Set<SubmittedAnswer> submittedAnswers){
			Map<Category, List<SubmittedAnswer>> test = submittedAnswers.stream().collect(Collectors.groupingBy(a-> a.getQuestion().getCategory()));
			//List<String> test = submittedAnswers.stream().map(a -> a.getQuestion().getCategory()
			
			
			System.out.println("testing with maps start");
			test.forEach((a, b)-> System.out.println(a.getCategory() + " " + b.stream().map(c-> c.getQuestion().getQuestionText()).collect(Collectors.toList()) + "\n"));
			System.out.println("testing with maps finish");
			
			//Print individual list items with nested method as a stream.
			for (Map.Entry<Category, List<SubmittedAnswer>> entry : test.entrySet()){
				System.out.println(entry.getKey().getCategory());
				for (SubmittedAnswer ans : entry.getValue()) {
					//Need instanceof check here
					if (ans instanceof SubmittedAnswerMultiImpl) {
						SubmittedAnswerMultiImpl subMultiImpl = (SubmittedAnswerMultiImpl)ans;
						System.out.println("Question: " +  subMultiImpl.getQuestion().getQuestionText() + "\n User: " 
						+ subMultiImpl.getUser().getFirstName() + "\n Id: " + subMultiImpl.getId());
						
						//subMultiImpl.getSelectedAnswers().forEach(System.out::println);
						//+ " SelectedAnswers: " + subMultiImpl.getSelectedAnswers().forEach(System.out::println));
					}
				}		 
			}

			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		//Learn code: Learn how to return to Set, i.e. Map<Category, Set<SubmittedAnswer>> // Not simple
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch3(Set<SubmittedAnswer> submittedAnswers){
			Map<Category, List<SubmittedAnswer>> test = submittedAnswers.stream().collect(Collectors.groupingBy(a-> a.getQuestion().getCategory()));
			//List<String> test = submittedAnswers.stream().map(a -> a.getQuestion().getCategory()
			test.forEach((a, b)-> System.out.println(a + " " + b.size() + "\n"));
															
			//Print individual list items with nested method as a stream.
			for (Map.Entry<Category, List<SubmittedAnswer>> entry : test.entrySet()){
				System.out.println(entry.getKey().getCategory());
				for (SubmittedAnswer ans : entry.getValue()) {
					//Need instanceof check here
					if (ans instanceof SubmittedAnswerMultiImpl) {
						SubmittedAnswerMultiImpl subMultiImpl = (SubmittedAnswerMultiImpl)ans;
						System.out.println("Question: " +  subMultiImpl.getQuestion().getQuestionText() + "\n User: " 
						+ subMultiImpl.getUser().getFirstName() + "\n Id: " + subMultiImpl.getId());
						
						//subMultiImpl.getSelectedAnswers().forEach(System.out::println);
						//+ " SelectedAnswers: " + subMultiImpl.getSelectedAnswers().forEach(System.out::println));
					}
				}		 
			}

			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch2(Set<SubmittedAnswer> submittedAnswers){
			Map<Object, List<SubmittedAnswer>> test = submittedAnswers.stream().collect(Collectors.groupingBy(a-> a.getClass().getName()));
			//List<String> test = submittedAnswers.stream().map(a -> a.getQuestion().getCategory()
			test.forEach((a, b)-> System.out.println(a + " " + b.size() + "\n"));
			
			//Print individual list items. Maybe use a range method.
			
			
			
			
			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		//Problem could be using an interface?
		private static Map<Category,Set<SubmittedAnswer>> calculateMatch1(Set<SubmittedAnswer> submittedAnswers){
			List<String> test = submittedAnswers.stream().map(a -> a.getClass().getName()).collect(Collectors.toList());
			test.forEach(System.out::println);
			
			//submittedAnswers.stream().map(SubmittedAnswer::getUser()collect(Collectors.groupingBy(submittedAnswers::getUser());
			return null;
			
		}
		
		
		//Separate/Partition by category
			//Use map function for if statements. Extract into method reference 
			//If instanceof AnswerWeightedimpl
				//Create map, <User, Double percentage>
					//Testing, use double with margin for error. e.g. hamcrest Closeto method.
				
				//calculate matches method (could be added as a filter? via method reference?)BiFunction?
					//Check to see if weight is set, if not, skip/continue
				//Find difference between each score, if diff 0 100%, if difference 5-0% match
					//find weight of Person A, find weight of Person B, map subtract one from other, 
					//map convert into percentage double
					//make sure ends up as stream, so can plug in the method to larger method?
				//could be adding to existing map? (Pearl: Only calculate newly-answered questions/uncached results)
		
			//If instanceof SubmittedAnswerMulti (AnswerWeighted)
					//Need to add to same map as previous one. if same answer 100%, if diff answer 0% this matching algorithm seems to harsh. Maybe user doesn't care about other person beig difefrent.
					//Create new map of answers. <User, Double percentage>
					//Merge this map with previous map, combination part calculates new percentage based on the two percentages for each map
					//Separate methods for? /Take into account if multianswer or singleanswer. Maybe SubmittedAnswerMulti should be subclass of SubmittedAnswerSingle? Or maybe they should implement a common inetrface. Abstract class Submitetd Answer
					
			//If instance of SubmittedAnswerMulti (singleAnswer) - match formula different
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
		
		
//		private void matchPercentageByCategoryOld(User searchingUser, User comparedUser){
//			//Improve, maybe map already exists in database. Get from there, use caching and only calculate changed values?
//			Map<Category, Map<Question, AnswerWeight>> matchScoresByCategory;
//			
//			Map<String, SubmittedAnswer> searchingUserAnswers = searchingUser.getSubmittedAnswers();
//			Map<String, SubmittedAnswer> comparedUserAnswers = searchingUser.getSubmittedAnswers();
//			
//			//String is questionText
//			//Don't like have to loop through all for each question. Should be able to access directly.
//			//Need add a check that SubmittedAnswer is of the right type.
//			for (Map.Entry<String, SubmittedAnswer> pair : searchingUserAnswers.entrySet()) {
//				SubmittedAnswer searchingUserAns = pair.getValue();
//				String searchingUserQuestionText = searchingUserAns.getQuestion().getQuestionText();
//				
//				SubmittedAnswer comparedUserAns = comparedUserAnswers.get(searchingUserQuestionText);
//				
//				//do null check
//				if (comparedUserAns == null) {
//					continue; //Not all users will have submitted the same answers. If no matching SubmitetdAnswer, go on to the next one.
//				}
//				
//				Set<Answer> searchingUserSelectedAnswers = searchingUserAns.get
//				
//					//String searchingUserQuestionText = ans.getQuestion().getQuestionText();
//					//Use above String to find matching Submitted Answer from commparedUser
//					//Loop though selectedAnswers
//					//get category from SubmittedAnswer.question.category
//					//If you insert into a map key/category that doesn't/already exists, what happens?
//								
//			}
//			
//			
//			submittedAnswers.stream().map(a-> {
//				if (a instanceof SubmittedAnswerMultiImpl) {
//					//Method to calculate match
//
//					//get selected answers. Loop through and do instanceof check again
//					Set<Answer> subAnsMultiImplSet = ((SubmittedAnswerMultiImpl) a).getSelectedAnswers();
//					
//					subAnsMultiImplSet.stream()
//					.map((b)-> { 
//						if (b instanceof AnswerWeightedImpl) {
//							//Extract to local method?
//							
//							
//						
//							
//							return b; //Return Map <User, Double>
//						} else if (b instanceof AnswerImpl) {
//							
//							return b; //Return Map <User, Double>
//						} else {
//							
//							return b; //Return Map <User, Double>
//						}		
//					}
//					).forEach(System.out::println); //Change this line
//					
//		
//				} else if (a instanceof SubmittedAnswerSingleImpl){
//					return a;
//				} else {
//					return a;
//				}
//				
//			}	
//			) //Continue stream logic here
//			
//			//get instance of SubmittedAnsMulti
//			//All go into the same map, regardless of question type.
//			//What changes with question type is the matching algorithm
//			
//			return null;
//		}
		
		
		
}		
