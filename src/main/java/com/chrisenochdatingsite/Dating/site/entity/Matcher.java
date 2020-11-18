package com.chrisenochdatingsite.Dating.site.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.Map.Entry;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;
import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswer;


public class Matcher {
		//Improve code: Add id field here?
		private Set<SubmittedAnswer> submittedAnswers;	
		private User searchingUser;
		private Map<User, LinkedHashMap<User, Integer>> totalMatchPercentagesByUser = new HashMap<>();
		private Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByCategory = new HashMap<>();
		private Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer = new HashMap<>();
		//private Map<Category, LinkedHashMap<User, Integer>>
		private Map<Category, Map<User, Integer>>totalMatchPercentagesByCategoryAndUser = new LinkedHashMap<>();
		
		
		
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

		 * Do iteratively first and then change to streams.

		 */

		public Map<Category, Map<User, Integer>> getTotalMatchPercentagesByCategoryAndUser() {
			return totalMatchPercentagesByCategoryAndUser;
		}

		public void setTotalMatchPercentagesByCategoryAndUser(
				Map<Category, Map<User, Integer>> totalMatchPercentagesByCategoryAndUser) {
			this.totalMatchPercentagesByCategoryAndUser = totalMatchPercentagesByCategoryAndUser;
		}

		public Set<SubmittedAnswer> getSubmittedAnswers() {
			return submittedAnswers;
		}

		public void setSubmittedAnswers(Set<SubmittedAnswer> submittedAnswers) {
			this.submittedAnswers = submittedAnswers;
		}

		public User getSearchingUser() {
			return searchingUser;
		}

		public void setSearchingUser(User searchingUser) {
			this.searchingUser = searchingUser;
		}

		public Map<User, LinkedHashMap<User, Integer>> getTotalMatchPercentagesByUser() {
			return totalMatchPercentagesByUser;
		}

		public void setTotalMatchPercentagesByUser(Map<User, LinkedHashMap<User, Integer>> totalMatchPercentagesByUser) {
			this.totalMatchPercentagesByUser = totalMatchPercentagesByUser;
		}

		public Map<User, LinkedHashMap<Category, Integer>> getTotalMatchPercentageByCategory() {
			return totalMatchPercentageByCategory;
		}

		public void setTotalMatchPercentageByCategory(
				Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByCategory) {
			this.totalMatchPercentageByCategory = totalMatchPercentageByCategory;
		}

		public Map<Category, Map<Question, Map<String, Integer>>> getMatchPercentageByCategoryAndAnswer() {
			return matchPercentageByCategoryAndAnswer;
		}

		public void setMatchPercentageByCategoryAndAnswer(
				Map<Category, Map<Question, Map<String, Integer>>> matchPercentageByCategoryAndAnswer) {
			this.matchPercentageByCategoryAndAnswer = matchPercentageByCategoryAndAnswer;
		}

		
		private LinkedHashMap<User, Integer> sortByPercentageDescending (LinkedHashMap<User, Integer> totalMatchPercentagesByUser
				, Comparator<Entry<User, Integer>> valueComparator) {
			//Set<Map.Entry<User, Integer>> entries = totalMatchPercentagesByUser.entrySet();
			Set<Entry<User, Integer>> entries = totalMatchPercentagesByUser.entrySet();
			List<Entry<User, Integer>> listOfEntries = new ArrayList<Entry<User, Integer>>(entries);
			
			//Collections.sort(listOfEntries, Comparator.comparing((Integer a)-> a.intValue()));
			Collections.sort(listOfEntries, valueComparator);
			
			LinkedHashMap<User, Integer> sortedByValue = new LinkedHashMap<User, Integer>(listOfEntries.size());
			// copying entries from List to Map
	        for(Entry<User, Integer> entry : listOfEntries){
	            sortedByValue.put(entry.getKey(), entry.getValue());
	        }
	        
	        return sortedByValue;
	        
		}
		
		
		public void updateTotalMatchPercentagesByUser(User userToAdd, LinkedHashMap<User, Integer>
				totalMatchPercentagesByUser , LinkedHashMap<Category, Integer> totalMatchPercentageByCategory){	
			//Map added as argument to avoid many object creations as this method could process tens of thousands of users.
			
			//Integer total = matchPercentageByCategory.entrySet().stream().map(a-> a.getValue()).reduce(0, Integer::sum);
			OptionalDouble average = totalMatchPercentageByCategory.entrySet().stream().mapToInt(categoryTotal-> categoryTotal.getValue()).average();
			
			if (average.isEmpty()) {
				//throw eception here. //Improve code: Add exception. Customised exception?
			}
			
			Long temp = Math.round(average.getAsDouble()); //Improve code. Perhaps change total var in map from Integer to Double
			int averageAsInt =  temp.intValue();
			totalMatchPercentagesByUser.put(userToAdd, averageAsInt);
			
			//Sort map so that higher match percentages are displayed first.
			LinkedHashMap<User, Integer> sortedMap = sortByPercentageDescending(totalMatchPercentagesByUser, new Matcher().new ValueComparator()); //Improve code
			
			//
			//Update original map with values of the sorted map.
			totalMatchPercentagesByUser.clear();
			totalMatchPercentagesByUser.putAll(sortedMap);

		}
		

		
		public LinkedHashMap<Category, Integer> totalMatchPercentageByCategory(Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer) {
			//Change to mapToInt
//			 matchPercentageByCategory.entrySet().stream().map(a-> a.getValue().entrySet().stream()
//					.map(b-> b.getValue().entrySet().stream().mapToInt(c->c.getValue()).sum()));
							
			 LinkedHashMap<Category, Integer> totals = new LinkedHashMap<>();
			 Double sum = 0.0;
			 for (Map.Entry pair : matchPercentageByCategoryAndAnswer.entrySet()) {	 
				 Category category = (Category) pair.getKey();
				 int answerCount = 0;
				 for (Map.Entry pair2 : ((Map<String, Answer>) pair.getValue()).entrySet()) {
					 for (Map.Entry pair3 :  ((Map<String, Answer>) pair2.getValue()).entrySet()) {
						 sum += (Integer)pair3.getValue();
						 System.out.println("Sum" + sum);
						 answerCount++;
					 }
									 
				 }	

				 sum = Math.ceil(sum/answerCount);
				 int sumIntValue = sum.intValue(); //Avoid integer division	 
				 totals.put(category, sumIntValue);
				 sum = 0.0;
				 answerCount = 0;
			 }

			return totals;
		}
		
	
		
		
//		Return sorted list of best matches, all categories considered sorted by best to worst matching.
		
//		Map<User,Map<Category, Map<Question,Map<String,Integer>>>>
//		Method structure
		//get all Users (separate method). This becomes outerloop
		//matchPercentageByCategory, searchignUser stays same, comparingUser changes depending on outerloop
		//Results of previous method put into totalMatchPercentageByCategory method
		//Add info to map: Map<Category, Map<User>, total>
		//When user loop finished return Map<Category, TreeMap<User>, total> //Or some sorted map,
		//Separate method: Total can be got from previous map easily? hash lookup so should be fast?
			//Average total of all categories for each user and put into TreeMap?
		//Could fill in two maps on one pass through loop but breaks single responsibility principle.
		
		//Go through matchPercentagebyCategory, return: Map<Category, Map<Question,Map<String,Integer>>>
		//enter result into total method 
		//Extra method to then extract results from total and add to: Map<Category, Map<User>, Integer  total> and returns this
		//Another method takes previous returned statement as argument: Method to get absolute totals and put into another map
		//add User into new map, along with corresponding total Map<Category, Map<User>, total>
		// If comparing all, do not need keep record of answers and questions, so Map<Category, Map<User, Integer total>>
		
		//Problem is that userToAdd and totalMatchPercentageByCategory may end up out of sync
		//User has knowledge of a MatchInfo class which holds all this info
		//Keep this as match calculation class? 
		
		//Will need to instantiate new Match objects every loop. Is this necessary?
		//get Users
		//Get all of their match objects
		//Check userToAdd and totalMatchPercentageByCategory don't equal null, if they do, throw exception
		public void updateTotalMatchPercentagesByCategoryAndUser(User userToAdd, LinkedHashMap<Category, 
				Integer> totalMatchPercentageByCategory, Map<Category, Map<User, Integer>>
				totalMatchPercentagesByCategoryAndUser){ //Last field would be got from User map, which is updated every session.Add the results to the maps of MatchInfo.
			
			//Problem: If same category, would overwrite all values. SO if category exists
				//Solution: Get the map by category and then add
			//How get the map by category? 
			//Loop over them, all categories may not coincide if answered different questions
			
			//Map<Category, Map<User, Integer>>
		
			for (Map.Entry<Category, Integer> map: totalMatchPercentageByCategory.entrySet()){
				Category category = (Category) map.getKey();
				
				//What if doesn't already exist?
				Map<User, Integer> scoresByUser = totalMatchPercentagesByCategoryAndUser.get(category);
				if (scoresByUser == null) {
					scoresByUser = new HashMap<>();
				}
				
				//add score to scoresByUser
				//if (scoresByUser == null) {
					scoresByUser.put(userToAdd, (Integer) map.getValue());
				//} //else {
					scoresByUser.put(userToAdd, scoresByUser.get(userToAdd));
				//}
				
				
				
				//add to category
				totalMatchPercentagesByCategoryAndUser.put(category, scoresByUser); 
				
			}
			
			//Will also need to sort
			//Will need to be LinkedHashMap. First get working, then sort.
			
			
			
		}
		
		
		public Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer(User searchingUser
				, User comparedUser, Function<Integer,Integer> convertWeightedAns, Function<Boolean,Integer> convertCheckboxAns) throws Exception{ //String = answerText. Improve code: update this to ANswerText class
			//Improve, maybe map already exists in database. Get from there, use caching and only calculate changed values?
			Map<Category, Map<Question,Map<String,Integer>>> matchWeightsByCategory = new HashMap<>(); //Integer = diffInWeight
			Map<String, SubmittedAnswer> searchingUserSubmittedAnswers = searchingUser.getSubmittedAnswers();
			Map<String, SubmittedAnswer> comparedUserSubmittedAnswers = comparedUser.getSubmittedAnswers();
			
			if (searchingUserSubmittedAnswers == null) {
				throw new Exception(searchingUser.getFirstName() + " has not submitted any answers so compatibility cannot be calculated.");
			}
			if (comparedUserSubmittedAnswers == null) {
				throw new Exception(comparedUser.getFirstName() + " has not submitted any answers so compatibility cannot be calculated.");
			}

			
			for (Map.Entry<String, SubmittedAnswer> pair : searchingUserSubmittedAnswers.entrySet()) { //String is questionText
				SubmittedAnswer searchingUserAns = pair.getValue();
				
				if (searchingUserAns instanceof SubmittedAnswerMultiImpl) {
					SubmittedAnswerMultiImpl searchingUserAnsMultiImpl = (SubmittedAnswerMultiImpl) searchingUserAns;
					
					String searchingUserQuestionText = searchingUserAns.getQuestion().getQuestionText();		
					SubmittedAnswer comparedUserAns = comparedUserSubmittedAnswers.get(searchingUserQuestionText);
					 
					SubmittedAnswerMultiImpl comparedUserAnsMultiImpl = (SubmittedAnswerMultiImpl) comparedUserAns;
					
					//do null check
					if (comparedUserAns == null) {
						continue; //Not all users will have submitted the same answers. If no matching SubmittedAnswer, go on to the next one.
					}
		
					Map<String, Answer> searchingUserSelectedAnswers = searchingUserAnsMultiImpl.getSelectedAnswers();
					Map<String, Answer> comparedUserSelectedAnswers = comparedUserAnsMultiImpl.getSelectedAnswers();
					
					//Loop through and compare scores and add to right category.
					
					for (Map.Entry<String, Answer> map : searchingUserSelectedAnswers.entrySet()) {
						Answer ans = map.getValue();
						
						int convertedScore;
						if (ans instanceof AnswerWeightedImpl) {
							
							AnswerWeightedImpl searchingUserAnsWeighted = (AnswerWeightedImpl) ans;

							AnswerWeightedImpl comparedUserAnswerWeighted = (AnswerWeightedImpl) comparedUserSelectedAnswers.get(searchingUserAnsWeighted.getAnswerText());
							int diffInWeight; 
							if (comparedUserAnswerWeighted != null) {								
								diffInWeight = Math.abs(searchingUserAnsWeighted.getAnswerWeight().getWeight() - comparedUserAnswerWeighted.getAnswerWeight().getWeight());
							
								convertedScore = convertWeightedAns.apply(diffInWeight);
							
							} else {
								//throw exception. Do custom exception? / continue loop?
								continue;
							}
							
							
						} else if (ans instanceof AnswerImpl) { 
							//loop through 

							boolean isMatch = scoreAnswerImpls(comparedUserSelectedAnswers, ans); 
							
							convertedScore = convertCheckboxAns.apply(isMatch);
							
		
						} else {
							//Do logic for other answer types later
						  continue; // THIS MUST BE CHANGED. ONLY ADDED TEMPRARILY WHILE NO OTHER ANSWER TYPES.
						}

							//Get category and add score to Map<Category, Map<Question, AnswerWeight>> matchWeightsByCategory;
							Category category = searchingUserAns.getQuestion().getCategory();
							Question question = searchingUserAns.getQuestion();
							//Improve code: get comparedUser category and only proceed if categories match? Perhaps unnecessary
							
							//Dont overwrite current entries for specified category
							addScoresToMap(matchWeightsByCategory, ans, convertedScore, category, question);
								
					}
					
			
				} else { //If not instanceof SubmittedAnswerMultiImpl		
						}							
			}
			
			return matchWeightsByCategory;
		}




		private void addScoresToMap(Map<Category, Map<Question, Map<String, Integer>>> matchWeightsByCategory,
				Answer ans, int convertedScore, Category category, Question question) {
			if (matchWeightsByCategory.containsKey(category)) {
				
				if (matchWeightsByCategory.get(category).containsKey(question)) {
					//fetch answer map
					Map<String, Integer> tempAnswerMap = matchWeightsByCategory.get(category).get(question);
					tempAnswerMap.put(ans.getAnswerText(), convertedScore);
					
					//add answer to question map
					
					Map<Question,Map<String, Integer>> tempMapWithQuestion = new HashMap<>(); 
					tempMapWithQuestion.put(question, tempAnswerMap);
					
					//add to category map
					//Overwrite category key in map with new updated question (and thus answer) information
					matchWeightsByCategory.put(category,tempMapWithQuestion);
						
				} else { //if contains category but doesn't contain question
					//Add new answer to answer map
					Map<String, Integer> tempAnswerMap = new HashMap<>();
					tempAnswerMap.put(ans.getAnswerText(), convertedScore);
					
					//Add question to appropriate category
					Map<Question,Map<String, Integer>> tempQuestionMap = matchWeightsByCategory.get(category);
					tempQuestionMap.put(question, tempAnswerMap);
					
					matchWeightsByCategory.put(category,tempQuestionMap);
					
				}					
				
			} else { //doesn't contain category
				Map<String, Integer> tempMap = new HashMap<>(); 
				tempMap.put(ans.getAnswerText(), convertedScore);
				
				//Create question key in map and add answer information
				Map<Question,Map<String, Integer>> tempMapWithQuestion = new HashMap<>(); 
				tempMapWithQuestion.put(question, tempMap);
				
				//Create category key in map and add question (and thus answer) information
				matchWeightsByCategory.put(category,tempMapWithQuestion);
			}
		}




		private boolean scoreAnswerImpls(Map<String, Answer> comparedUserSelectedAnswers, Answer ans) {
			int count = 0;
			boolean isMatch;
			for (Map.Entry<String, Answer> map2 : comparedUserSelectedAnswers.entrySet()) {
				if (map2.getValue() instanceof AnswerImpl && ans.equals(map2.getValue()) ) { //Debugging: Will probably have to override equals method (and thus hash), compare by id
					count++;
				}
			}
			if (count >= 1) {
				//If there should be duplicate answers, the score will only get counted once because after the first time, the entry int he map below ill simply be overwritten.
				//100% match, add to score variable. Score variable will then go through functional interface method to convert the score
				//Add logging for duplicate answers to warn of potential problems? i.e. if count >= 1 ? Great idea, but extra step in the code...
				isMatch = true;
				System.out.println("isMatch: " + isMatch + " " + ans.getAnswerText());
			
			} else  {
				//0% match, add to score variable. Score variable will then go through functional interface method to convert the score
				isMatch = false;
				System.out.println("isMatch: " + isMatch + " " + ans.getAnswerText());
			}
			return isMatch;
		}
		

		class ConvertToPercent implements Function<Integer, Integer> {
			@Override
			public Integer apply(Integer t) {
				double percentage;
				
				switch(t) {
				
				case 0: 
					percentage = 6.0/6;
					System.out.println(percentage);
					break;
					
				case 1:
					percentage = 5.0/6;
					System.out.println(percentage);
					break;
				case 2: 
					percentage = 4.0/6;
					System.out.println(percentage);
					break;
					
				case 3:
					percentage = 3.0/6;
					System.out.println(percentage);
					break;
				case 4: 
					percentage = 2.0/6;
					System.out.println(percentage);
					break;
					
				case 5:
					percentage = 1.0/6;
					System.out.println(percentage);
					break;
				
				case 6: 
					percentage = 0;
					System.out.println(percentage);
					break;
					
				default: 
					percentage = 3.0/6; //Improve code. This switch statement should take an enum of AnswerWeighted?
				
				}
				System.out.println(percentage);
				percentage *= 100;
				System.out.println(percentage);
				percentage = Math.ceil(percentage);
				
				return (int) percentage;
			}

		}
		
		private class ValueComparator implements Comparator<Entry<User, Integer>> {          
			 @Override
	            public int compare(Entry<User, Integer> e1, Entry<User, Integer> e2) {
	                Integer v1 = e1.getValue();
	                Integer v2 = e2.getValue();	            	
//	            	String v1 = e1.getValue();
//	                String v2 = e2.getValue();
	                return v2.compareTo(v1);
	            }      
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
