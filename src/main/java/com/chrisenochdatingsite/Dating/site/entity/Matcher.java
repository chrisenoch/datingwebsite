  package com.chrisenochdatingsite.Dating.site.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;

import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswer;
import com.chrisenochdatingsite.Dating.site.util.NoAmountFoundException;
import com.chrisenochdatingsite.Dating.site.util.NoAnswersSubmittedException;
import com.chrisenochdatingsite.Dating.site.util.NoEquivalentAnswerException;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitor;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitorHelper;
import com.chrisenochdatingsite.Dating.site.visitor.AnswerVisitorImpl;


public class Matcher {
	
		private User searchingUser;
		
		//Need questions to be in order
	
		private Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		private LinkedHashMap<User, Integer> totalMatchPercentagesByUser = new LinkedHashMap<>();
		private Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser = new LinkedHashMap<>();
			
		public User getSearchingUser() {
			return searchingUser;
		}

		public void setSearchingUser(User searchingUser) {
			this.searchingUser = searchingUser;
		}

		public LinkedHashMap<User, Integer> getTotalMatchPercentagesByUser() {
			return totalMatchPercentagesByUser;
		}

		public void setTotalMatchPercentagesByUser(LinkedHashMap<User, Integer> totalMatchPercentagesByUser) {
			this.totalMatchPercentagesByUser = totalMatchPercentagesByUser;
		}

		public Map<User, LinkedHashMap<Category, Integer>> getTotalMatchPercentageByUserForEveryCategory() {
			return totalMatchPercentageByUserForEveryCategory;
		}

		public void setTotalMatchPercentageByUserForEveryCategory(
				Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory) {
			this.totalMatchPercentageByUserForEveryCategory = totalMatchPercentageByUserForEveryCategory;
		}

		public Map<Category, LinkedHashMap<User, Integer>> getTotalMatchPercentagesByCategoryForEveryUser() {
			return totalMatchPercentagesByCategoryForEveryUser;
		}

		public void setTotalMatchPercentagesByCategoryForEveryUser(
				Map<Category, LinkedHashMap<User, Integer>> totalMatchPercentagesByCategoryForEveryUser) {
			this.totalMatchPercentagesByCategoryForEveryUser = totalMatchPercentagesByCategoryForEveryUser;
		}


		public void  updateAllMatches(List<User> users, Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, Function<Integer,Integer> convertWeightedAns
				, Function<Boolean,Integer> convertCheckboxAns ) {
			
			for (User user : users) {
				
				//Do not compare searchingUser with him/herself
				if (user.equals(this.searchingUser)) { 
					continue;
				}
				
				
				Map<Category, Map<Question, Map<String, Integer>>> matchPercentageByCategoryAndAnswer;
				try {
					matchPercentageByCategoryAndAnswer = matchPercentageByCategoryAndAnswer(this.searchingUser
								, user, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, convertWeightedAns, convertCheckboxAns);
				
					LinkedHashMap<Category, Integer> totalMatchPercentageByCategory = totalMatchPercentageByCategory(matchPercentageByCategoryAndAnswer);
					
					updateTotalMatchPercentagesByUser(user, totalMatchPercentageByCategory);
					
					updateTotalMatchPercentagesByCategoryForEveryUser(user,totalMatchPercentageByCategory);
		
					updateTotalPercentageByUserForEveryCategory(user, totalMatchPercentageByCategory);
				
				
				} catch (NoAnswersSubmittedException e) {
					System.out.println("Debugging: + entered catch: NoAnswersSubmittedException" + user + " " + this.searchingUser);
					
					e.printStackTrace();
					continue;
				} catch (NoAmountFoundException e) {
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			
		}
		
		
		private LinkedHashMap<User, Integer> sortByPercentageDescending (LinkedHashMap<User, Integer> 
		totalMatchPercentagesByUser) {
			
			Set<Entry<User, Integer>> entries = totalMatchPercentagesByUser.entrySet();
			List<Entry<User, Integer>> listOfEntries = new ArrayList<Entry<User, Integer>>(entries);
			
			Collections.sort(listOfEntries, new ValueComparator());
			
			LinkedHashMap<User, Integer> sortedByValue = new LinkedHashMap<User, Integer>(listOfEntries.size());
			// copying entries from List to Map
	        for(Entry<User, Integer> entry : listOfEntries){
	            sortedByValue.put(entry.getKey(), entry.getValue());
	        }
	        
	        return sortedByValue;
	        
		}
			
		
		public void updateTotalMatchPercentagesByUser(User userToAdd, LinkedHashMap<Category, Integer> 
		totalMatchPercentageByCategory) throws Exception{	
			//Map added as argument to avoid many object creations as this method could process tens of thousands of users.
			
			//Integer total = matchPercentageByCategory.entrySet().stream().map(a-> a.getValue()).reduce(0, Integer::sum);
			OptionalDouble average = totalMatchPercentageByCategory.entrySet().stream().mapToInt(categoryTotal-> categoryTotal.getValue()).average();
			
			if (average.isEmpty()) {
				throw new NoAmountFoundException("More information needed in order to calculate match percentage.");
			}
			
			Long temp = Math.round(average.getAsDouble()); //Improve code. Perhaps change total var in map from Integer to Double
			int averageAsInt =  temp.intValue();
			totalMatchPercentagesByUser.put(userToAdd, averageAsInt);
			
			//Sort map so that higher match percentages are displayed first.
			LinkedHashMap<User, Integer> sortedMap = sortByPercentageDescending(totalMatchPercentagesByUser); //Improve code
			
			//
			//Update original map with values of the sorted map.
			totalMatchPercentagesByUser.clear();
			totalMatchPercentagesByUser.putAll(sortedMap);
			
			//this.setTotalMatchPercentagesByUser(totalMatchPercentagesByUser);

		}
		
		public void updateTotalMatchPercentagesByCategoryForEveryUser(User userToAdd, LinkedHashMap<Category, 
				Integer> totalMatchPercentageByCategory){ //Last field would be got from User map, which is updated every session.Add the results to the maps of MatchInfo.
		
			for (Map.Entry<Category, Integer> map: totalMatchPercentageByCategory.entrySet()){
				Category category = (Category) map.getKey();
				
				//What if doesn't already exist?
				LinkedHashMap<User, Integer> scoresByUser = totalMatchPercentagesByCategoryForEveryUser.get(category);
				if (scoresByUser == null) {
					scoresByUser = new LinkedHashMap<>();
				}

				scoresByUser.put(userToAdd, (Integer) map.getValue()); 
				
				//Sort map so that higher match percentages are displayed first.
				LinkedHashMap<User, Integer> sortedMap = sortByPercentageDescending(scoresByUser); //Improve code
				
				//
				//Update original map with values of the sorted map.
				scoresByUser.clear();
				scoresByUser.putAll(sortedMap);
				

				//add to category
				totalMatchPercentagesByCategoryForEveryUser.put(category, scoresByUser); 			
				
				//this.setTotalMatchPercentagesByCategoryForEveryUser(totalMatchPercentagesByCategoryForEveryUser);
				
			}
		}
		
		public void updateTotalPercentageByUserForEveryCategory(User userToAdd, LinkedHashMap<Category, Integer> totalMatchPercentageByCategory) {
			
			totalMatchPercentageByUserForEveryCategory.put(userToAdd, totalMatchPercentageByCategory);
			
			
		}
		

		
		public LinkedHashMap<Category, Integer> totalMatchPercentageByCategory(Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer) {
			//Change to mapToInt
//			 matchPercentageByCategory.entrySet().stream().map(a-> a.getValue().entrySet().stream()
//					.map(b-> b.getValue().entrySet().stream().mapToInt(c->c.getValue()).sum()));
							
			 LinkedHashMap<Category, Integer> totals = new LinkedHashMap<>();
			 Double sum = 0.0;
			 for (Map.Entry<Category, Map<Question,Map<String,Integer>>> pair : matchPercentageByCategoryAndAnswer.entrySet()) {	 
				 Category category = (Category) pair.getKey();
				 int answerCount = 0;
				 for (Map.Entry<Question,Map<String,Integer>> pair2 : pair.getValue().entrySet()) {
					 for (Map.Entry<String,Integer> pair3 :   pair2.getValue().entrySet()) {
						 sum += (Integer)pair3.getValue();
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
		
		
		public Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer(User searchingUser
				, User comparedUser, Map<Category, Map<Question,Map<String,Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, Function<Integer,Integer> convertWeightedAns, Function<Boolean,Integer> convertCheckboxAns) throws Exception{ //String = answerText. Improve code: update this to ANswerText class
			//Improve, maybe map already exists in database. Get from there, use caching and only calculate changed values?
			
			Map<Category, Map<Question,Map<String,Integer>>> matchScoresByCategory = prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero;
			
			List<SubmittedAnswer> searchingUserSubmittedAnswersList = searchingUser.getSubmittedAnswers()
					.orElseThrow(()-> new NoAnswersSubmittedException(searchingUser.getFirstName() 
					+ " has not submitted any answers so compatibility cannot be calculated."));
			List<SubmittedAnswer>  comparedUserSubmittedAnswersList = comparedUser.getSubmittedAnswers()
					.orElseThrow(()-> new NoAnswersSubmittedException(comparedUser.getFirstName() 
					+ " has not submitted any answers so compatibility cannot be calculated."));		
			
			Map<String, SubmittedAnswer> searchingUserSubmittedAnswers = convertToQuestionTextSubmittedAnswerMap(
					searchingUserSubmittedAnswersList);	
			
			Map<String, SubmittedAnswer> comparedUserSubmittedAnswers = convertToQuestionTextSubmittedAnswerMap(
					comparedUserSubmittedAnswersList);	
			
			
			for (Map.Entry<String, SubmittedAnswer> pair : searchingUserSubmittedAnswers.entrySet()) { //String is questionText
				SubmittedAnswer searchingUserAns = pair.getValue();
				
					SubmittedAnswerMultiImpl searchingUserAnsMultiImpl = (SubmittedAnswerMultiImpl) searchingUserAns;
					
					String searchingUserQuestionText = searchingUserAns.getQuestion().getQuestionText();		
					SubmittedAnswer comparedUserAns = comparedUserSubmittedAnswers.get(searchingUserQuestionText);
					 
					SubmittedAnswerMultiImpl comparedUserAnsMultiImpl = (SubmittedAnswerMultiImpl) comparedUserAns;
					
					if (comparedUserAns == null) { 
						continue; //Not all users will have submitted the same answers. If no matching SubmittedAnswer, go on to the next one.
					}
					
					Set<Answer> searchingUserSelectedAnswersSet = searchingUserAnsMultiImpl.getSelectedAnswers();
					Set<Answer> comparedUserSelectedAnswersSet = comparedUserAnsMultiImpl.getSelectedAnswers();		
					
					Map<String, Answer> searchingUserSelectedAnswers = convertToAnswerTextAnswerMap(
							searchingUserSelectedAnswersSet);
					Map<String, Answer> comparedUserSelectedAnswers = convertToAnswerTextAnswerMap(
							comparedUserSelectedAnswersSet);
											
					 
					 AnswerVisitorHelper ansVisitorHelper = new AnswerVisitorHelper(comparedUserSelectedAnswers , convertCheckboxAns
								, convertWeightedAns);
					 
					for (Map.Entry<String, Answer> map : searchingUserSelectedAnswers.entrySet()) {
						Answer ans = map.getValue();   

						int convertedScore;
						
						ans.setAnswerVisitorHelper(ansVisitorHelper);
						AnswerVisitor answerVisitor = new AnswerVisitorImpl();
						
						try {
							convertedScore = ans.accept(answerVisitor);
						} catch (NoEquivalentAnswerException exc) {
							continue;
						}

						//Get category and add score to Map<Category, Map<Question, AnswerWeight>> matchWeightsByCategory;
						Category category = searchingUserAns.getQuestion().getCategory();
						Question question = searchingUserAns.getQuestion();	
						
						//Dont overwrite current entries for specified category
						addScoresToMap(matchScoresByCategory, ans, convertedScore, category, question);
							
					}									
			}
			
			return matchScoresByCategory;
		}
		
		private void debugPrintMatchScoresByCategory(Map<Category, Map<Question,Map<String,Integer>>> matchScoresByCategory) {
			for (Map.Entry<Category, Map<Question,Map<String,Integer>>>map1 : matchScoresByCategory.entrySet()) {
				System.out.println("Category: " + map1.getKey());
				
				for (Map.Entry<Question,Map<String,Integer>>map2 : map1.getValue().entrySet()) {
					System.out.println("Question: " + map2.getKey());
					
					for (Map.Entry<String,Integer>map3 : map2.getValue().entrySet()) {
						System.out.println("AnswerTxt: " + map3.getKey() + " Weight: " + map3.getValue());
							
					}
					System.out.println("------------------------------");
				}	
			}
		}

		private Map<String, Answer> convertToAnswerTextAnswerMap(Set<Answer> userSelectedAnswersSet) {
			Map<String, Answer> userSelectedAnswers = new HashMap<>();
			for (Answer ans : userSelectedAnswersSet) {
				userSelectedAnswers.put(ans.getAnswerText(), ans);		
			}
			return userSelectedAnswers;
		}

		private Map<String, SubmittedAnswer> convertToQuestionTextSubmittedAnswerMap(
			List<SubmittedAnswer> submittedAnswersList) {
			Map<String, SubmittedAnswer> userSubmittedAnswers = new HashMap<>();	
				for (SubmittedAnswer ans: submittedAnswersList) {
				userSubmittedAnswers.put(ans.getQuestion().getQuestionText(), ans);
				}
			return userSubmittedAnswers;
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
				//If there should be duplicate answers, the score will only get counted once because after the first time, the entry in the map below will simply be overwritten.
				//100% match, add to score variable. Score variable will then go through functional interface method to convert the score
				//Add logging for duplicate answers to warn of potential problems? i.e. if count >= 1 ? Great idea, but extra step in the code...
				isMatch = true;
			
			} else  {
				//0% match, add to score variable. Score variable will then go through functional interface method to convert the score
				isMatch = false;
			}
			return isMatch;
		}
		

		public class ConvertToPercent implements Function<Integer, Integer> {
			@Override
			public Integer apply(Integer t) {
				double percentage;
				
				switch(t) {
				
				case 0: 
					percentage = 6.0/6;
					break;
					
				case 1:
					percentage = 5.0/6;
					break;
				case 2: 
					percentage = 4.0/6;
					break;
					
				case 3:
					percentage = 3.0/6;
					break;
				case 4: 
					percentage = 2.0/6;
					break;
					
				case 5:
					percentage = 1.0/6;
					break;
				
				case 6: 
					percentage = 0;
	
					break;
					
				default: 
					percentage = 3.0/6; //Improve code. This switch statement should take an enum of AnswerWeighted?
				
				}
				percentage *= 100;
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
		
	
		
		
		
}		
