package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Matcher;
import com.chrisenochdatingsite.Dating.site.entity.Matcher.ConvertToPercent;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.interfaces.Answer;
import com.chrisenochdatingsite.Dating.site.interfaces.Question;
import com.chrisenochdatingsite.Dating.site.util.NoAnswersSubmittedException;


@ExtendWith(UserWithSubmittedAnswersParameterResolver.class)
public class MatchersTests {
	
	private User dave;
	private User jane;
	private User peter;
	
	private Matcher matcher;
	private Map<Category, Map<Question, Map<String, Integer>>> matchesDave;
	
	private Category movies;
	private Category sports;
	private Category travel;
	
	private Question questionMovies;
	private Question questionSports;
	private Question questionTravel;
	
	private Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
	
	
	@BeforeEach
	void init(Map<String, User> users) {

		this.dave = users.get("Dave");
		this.peter = users.get("Peter");
		this.jane = users.get("Jane");
		
		this.matcher = new Matcher();
		this.matcher.setSearchingUser(peter);
		
		//One of the main methods to be tested in this class. 
		try {
			this.matchesDave = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), dave
					, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, new Matcher().new ConvertToPercent()
					, a -> a.booleanValue() == true? 100 : 0 );
	
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
				//e.getMessage();
			}

		
		//Setup for testing Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer
		this.movies = new Category( "Movies");
		this.sports = new Category( "Sports");
		this.travel = new Category( "Travel");
		
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
		
		this.questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
				, movieAnswerOptions, movies);
		this.questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
				, sportsAnswerOptions, sports);
		this.questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
				, travelAnswerOptions, travel);
		
	}
	
	@Test
	public void shouldReturnPercentageRoundedUp() {
		
		ConvertToPercent convertToPercent = new Matcher().new ConvertToPercent();
		
		int percentage = convertToPercent.apply(1);
		int defaultPercentage = convertToPercent.apply(100000);
		int defaultPercentageMinus = convertToPercent.apply(-5);
		
		assertEquals(84, percentage);
		assertEquals(50, defaultPercentage);
		assertEquals(50, defaultPercentageMinus);
	}
	
	
	public void shouldReturnCorrectNumberofCategoriesInMap() {
		assertThat(matchesDave).isNotEmpty().hasSize(3);
	}
	
	
	//Test use case
	@Test
	public void shouldReturnCorrectValuesDependingOnAnswerType() {	
		//Testing method matchPercentageByCategoryAndAnswer, which can be found in @BeforeEach
		
		//Collections gather data from the nested map returned by matchPercentageByCategoryAndAnswer and will be used for testing.
		Set<Category> categories = new HashSet<>();
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
		System.out.println("matches dave: " + matchesDave);
		
		//Add values to collections ready for testing 
		for (Map.Entry map1 : matchesDave.entrySet()) {
			
			System.out.println("Category: " + map1.getKey());
			
			categories.add((Category) map1.getKey());
			
			for (Map.Entry map2 : ((Map<String, Answer>) map1.getValue()).entrySet()) {
				System.out.println("Question: " + map2.getKey());
				
				questions.add((Question) map2.getKey());
				
				for (Map.Entry map3 : ((Map<String, Answer>) map2.getValue()).entrySet()) {
					System.out.println("AnswerTxt: " + map3.getKey() + " Weight: " + map3.getValue());
					
					answers.put((String)map3.getKey(), (Integer)map3.getValue());
						
				}
				System.out.println("------------------------------");
			}		

		}
			
		assertThat(categories).contains(movies, sports, travel);
		assertThat(questions).contains(questionMovies, questionSports, questionTravel);
		assertThat(answers).contains(entry("Basketball", 67), entry("Football", 67), entry("Swimming", 100)
				, entry("Action", 100), entry("Horror", 0), entry("Romance", 100)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				);
	}
	
	@Test
	public void shouldReturnZeroWhenNoAnswersMatch() {
		//Jane and Peter (the searchingUser) have no movie answers in common
		Map<Category, Map<Question, Map<String, Integer>>> matchesJane = null;
		Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero2 = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
		System.out.println("prep in method: " + prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero2);
		
		//Act
		try {
			matchesJane = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), jane
					, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero2, new Matcher().new ConvertToPercent()
					, a -> a.booleanValue() == true? 100 : 0 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//Collections gather data from the nested map matchesJane and will be used for testing.
		Set<Category> categories = new HashSet<>();
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
		//Add values to collections ready for testing 
		for (Map.Entry map1 : matchesJane.entrySet()) {
			System.out.println("Category: " + map1.getKey());
			
			categories.add((Category) map1.getKey());
			
			for (Map.Entry map2 : ((Map<String, Answer>) map1.getValue()).entrySet()) {
				System.out.println("Question: " + map2.getKey());
				
				questions.add((Question) map2.getKey());
				
				for (Map.Entry map3 : ((Map<String, Answer>) map2.getValue()).entrySet()) {
					System.out.println("AnswerTxt: " + map3.getKey() + " Weight: " + map3.getValue());
					
					answers.put((String)map3.getKey(), (Integer)map3.getValue());
						
				}
				System.out.println("------------------------------");
			}		

		}

		assertThat(categories).contains(movies, sports, travel);
		assertThat(questions).contains(questionMovies, questionSports, questionTravel);
		assertThat(answers).contains(entry("Action", 0), entry("Romance", 0), entry("Horror", 0));
		
		System.out.println(categories);
		System.out.println(questions);
		System.out.println(answers);		
		
	}

	
	//Test with SubmittedAnswerSingleImpl

		
	@Test
	public void shouldThrowExceptionIfNoAnswersSubmitted() {
		User noSubmittedAnswersHarold = new User("Harold", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
		User noSubmittedAnswersMike = new User("Mike", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
		
		
		Exception exc = assertThrows(NoAnswersSubmittedException.class, ()-> matcher.matchPercentageByCategoryAndAnswer(noSubmittedAnswersHarold, dave
				, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, new Matcher().new ConvertToPercent()
				, a -> a.booleanValue() == true? 100 : 0 ));
		
		assertEquals("Harold has not submitted any answers so compatibility cannot be calculated.", exc.getMessage());
		
		
		Exception exc2 = assertThrows(Exception.class, ()-> matcher.matchPercentageByCategoryAndAnswer(peter, noSubmittedAnswersMike
				, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, new Matcher().new ConvertToPercent()
				, a -> a.booleanValue() == true? 100 : 0 ));
		
		assertEquals("Mike has not submitted any answers so compatibility cannot be calculated.", exc2.getMessage());
		
	}
	
	@Test
	public void shouldReturnTotalPercentageByUserForEveryCategory() {
		//Arrange
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryJane = new LinkedHashMap<>();
		
		totalPercentagesByCategoryDave.put(movies, 45);
		totalPercentagesByCategoryDave.put(sports, 55);
		totalPercentagesByCategoryDave.put(travel, 65); 
		
		totalPercentagesByCategoryJane.put(movies, 15);
		totalPercentagesByCategoryJane.put(sports, 25);
		totalPercentagesByCategoryJane.put(travel, 40);
		
		//Act
		matcher.updateTotalPercentageByUserForEveryCategory(dave, totalPercentagesByCategoryDave);
		matcher.updateTotalPercentageByUserForEveryCategory(jane, totalPercentagesByCategoryJane);
		
		//Get updated map from Matcher() class
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = matcher.getTotalMatchPercentageByUserForEveryCategory();
		
		//Assert
		assertThat(totalMatchPercentageByUserForEveryCategory).hasSize(2).containsKeys(jane, dave)
		.containsValues(totalPercentagesByCategoryDave,totalPercentagesByCategoryJane );	
		
	}
	
	@Test
	public void shouldReturnUsersAndTotalScoresInDescendingOrder() throws Exception {
		//Init
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		totalPercentagesByCategoryDave.put(movies, 45);
		totalPercentagesByCategoryDave.put(sports, 55);
		totalPercentagesByCategoryDave.put(travel, 65); 
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		totalMatchPercentageByUserForEveryCategory.put(dave,totalPercentagesByCategoryDave);
		
		
		//Unordered - as final result should be in descending order
		LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
		totalMatchPercentagesByUser.put(jane, 5);
		totalMatchPercentagesByUser.put(peter, 23);
		
		//Method being tested
		matcher.updateTotalMatchPercentagesByUser(dave,  totalPercentagesByCategoryDave);
		
		//get updated field from Matcher class
		LinkedHashMap<User, Integer> totalMatchPercentagesByUserUpdated = matcher.getTotalMatchPercentagesByUser();
		
		//Expected results
		LinkedHashMap<User, Integer> totalPercentagesByUserOrdered = new LinkedHashMap<>();
		totalPercentagesByUserOrdered.put(dave,  55);
		totalPercentagesByUserOrdered.put(peter,  23);
		totalPercentagesByUserOrdered.put(jane,  5);


		assertThat(totalMatchPercentagesByUserUpdated).containsKeys(peter, dave, jane).containsExactlyEntriesOf( totalPercentagesByUserOrdered);
		
	}
	
	public void shouldReturnAverage() throws Exception {
		//Init
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		totalPercentagesByCategoryDave.put(movies, 45);
		totalPercentagesByCategoryDave.put(sports, 55);
		totalPercentagesByCategoryDave.put(travel, 65); 
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		totalMatchPercentageByUserForEveryCategory.put(dave,totalPercentagesByCategoryDave);		
		
		//Method being tested
		matcher.updateTotalMatchPercentagesByUser(dave,  totalPercentagesByCategoryDave);
		
		//get updated field from Matcher class
		LinkedHashMap<User, Integer> totalMatchPercentagesByUserUpdated = matcher.getTotalMatchPercentagesByUser();
		assertEquals(55, totalMatchPercentagesByUserUpdated.containsValue(55));
		
	}
	
	
	@Test
	public void shouldThrowExceptionIfAverageIsEmpty() throws Exception {
		//Init
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		totalMatchPercentageByUserForEveryCategory.put(dave,totalPercentagesByCategoryDave);
		
		
		Exception exc = assertThrows(Exception.class, ()-> matcher.updateTotalMatchPercentagesByUser(dave,  totalPercentagesByCategoryDave));
		assertEquals("More information needed in order to calculate match percentage.", exc.getMessage());	
	}
	
	@Test
	public void shouldReturnRoundedUpTotalMatchPercentageByCategory() throws Exception {
		//Arrange
		Map<Category, Map<Question,Map<String,Integer>>> map = new HashMap<>();
		
		//Average of answerMovies = 25/3, which = 8.333. This should be rounded up to 9.
		Map<String,Integer> answersMovies = new HashMap<>();
		answersMovies.put("Adventure", 5);
		answersMovies.put("Comedy", 10);
		answersMovies.put("Drama", 10);
		
		Map<String,Integer> answersSports = new HashMap<>();
		answersSports.put("Judo", 15);
		answersSports.put("BMX", 5);
		answersSports.put("Rowing", 25);
		
		Map<Question,Map<String,Integer>> questionsAndAnswersMovies = new HashMap<>();
		questionsAndAnswersMovies.put(questionMovies, answersMovies);
		
		Map<Question,Map<String,Integer>> questionsAndAnswersSports = new HashMap<>();
		questionsAndAnswersSports.put(questionSports, answersSports);
		
		map.put(movies,questionsAndAnswersMovies);
		map.put(sports, questionsAndAnswersSports);
		
		//Act
		LinkedHashMap<Category, Integer> totalMatchPercentageByCategory = matcher.totalMatchPercentageByCategory(map);
	
		//Assert
		assertEquals(9,totalMatchPercentageByCategory.get(movies));
		assertEquals(15,totalMatchPercentageByCategory.get(sports));
		 
	}
	
	@Test
	public void shouldReturnTotalMatchPercentagesByCategoryForEveryUserInDescendingOrder() {
		//Arrange
		LinkedHashMap<Category, Integer> totalMatchPercentageByCategoryDave = new LinkedHashMap<>();
		 totalMatchPercentageByCategoryDave.put(movies, 20);
		 totalMatchPercentageByCategoryDave.put(sports, 40);
		 totalMatchPercentageByCategoryDave.put(travel, 50);
		 
		 LinkedHashMap<Category, Integer> totalMatchPercentageByCategoryJane = new LinkedHashMap<>();
		 totalMatchPercentageByCategoryJane.put(movies, 50);
		 totalMatchPercentageByCategoryJane.put(sports, 10);
		 totalMatchPercentageByCategoryJane.put(travel, 70);
		 
		 //Act
		 matcher.updateTotalMatchPercentagesByCategoryForEveryUser(dave, totalMatchPercentageByCategoryDave);
		 matcher.updateTotalMatchPercentagesByCategoryForEveryUser(jane, totalMatchPercentageByCategoryJane);
		 
		 //get the field that updateTotalMatchPercentagesByCategoryForEveryUser updates in Matcher class
		 Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser 
		 = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
		 
		//get inner map to test
		 LinkedHashMap<User, Integer> moviesCategory = totalMatchPercentagesByCategoryForEveryUser.get(movies);
		 LinkedHashMap<User, Integer> sportsCategory = totalMatchPercentagesByCategoryForEveryUser.get(sports);
		 LinkedHashMap<User, Integer> travelCategory = totalMatchPercentagesByCategoryForEveryUser.get(travel);
		 
		 //Expected values
		 LinkedHashMap<User, Integer> moviesExpected = new LinkedHashMap<>();
		 LinkedHashMap<User, Integer> sportsExpected = new LinkedHashMap<>();
		 LinkedHashMap<User, Integer> travelExpected = new LinkedHashMap<>();
		 
		 moviesExpected.put(jane, 50);
		 moviesExpected.put(dave, 20);
		 sportsExpected.put(dave, 40);
		 sportsExpected.put(jane, 10);	 
		 travelExpected.put(jane, 70);
		 travelExpected.put(dave, 50);

		 //Act
		 assertThat(moviesCategory).containsExactlyEntriesOf(moviesExpected);
		 assertThat(sportsCategory).containsExactlyEntriesOf(sportsExpected);
		 assertThat(travelCategory).containsExactlyEntriesOf(travelExpected);
		 	
	}
	
	@Test
	@DisplayName("Should not add searchingUser to maps but should add all other users.")
	public void shouldNotAddSearchingUserToMaps() throws Exception {
		//Arrange
		List<User> users = Arrays.asList(peter, dave, jane);
		
		//Act
		matcher.updateAllMatches(users, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, new Matcher().new ConvertToPercent() , a -> a.booleanValue() == true? 100 : 0);
		
		//Get updated maps from Matcher class
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = matcher.getTotalMatchPercentageByUserForEveryCategory();
		LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
		Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
		
		//Get inner map from totalMatchPercentagesByCategoryForEveryUser in order to test for presence of searchingUser
		LinkedHashMap<User, Integer> movieCategory = totalMatchPercentagesByCategoryForEveryUser.get(movies);
		LinkedHashMap<User, Integer> sportsCategory = totalMatchPercentagesByCategoryForEveryUser.get(sports);
		LinkedHashMap<User, Integer> travelCategory = totalMatchPercentagesByCategoryForEveryUser.get(travel);
		
		assertThat(totalMatchPercentageByUserForEveryCategory).doesNotContainKeys(peter);
		assertThat(totalMatchPercentagesByUser).doesNotContainKeys(peter).containsKeys(dave, jane);
		assertThat(movieCategory).doesNotContainKey(peter).containsKeys(dave, jane);
		assertThat(sportsCategory).doesNotContainKey(peter).containsKeys(dave, jane);
		assertThat(travelCategory).doesNotContainKey(peter).containsKeys(dave, jane);
	}
	
	@Test
	@DisplayName("Should ignore any user who hasn't submitted any answers and calculate the match scores for the others.")
	public void shouldIgnoreUserIfUserHasNotSubmittedAnyAnswers() throws Exception {
		//Arrange
		User userWithNoAns = new User("Tom", "Smith", "tom@yahoo.com", LocalDate.now(), Sex.MALE);
		List<User> users = Arrays.asList(peter, dave, jane, userWithNoAns);
		
		//Act
		matcher.updateAllMatches(users, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, new Matcher().new ConvertToPercent() , a -> a.booleanValue() == true? 100 : 0);
		
		//Get updated maps from Matcher class
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = matcher.getTotalMatchPercentageByUserForEveryCategory();
		LinkedHashMap<User, Integer> totalMatchPercentagesByUser = matcher.getTotalMatchPercentagesByUser();
		Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
		
		//Get inner map from totalMatchPercentagesByCategoryForEveryUser in order to test for presence of searchingUser
		LinkedHashMap<User, Integer> movieCategory = totalMatchPercentagesByCategoryForEveryUser.get(movies);
		LinkedHashMap<User, Integer> sportsCategory = totalMatchPercentagesByCategoryForEveryUser.get(sports);
		LinkedHashMap<User, Integer> travelCategory = totalMatchPercentagesByCategoryForEveryUser.get(travel);
		
		assertThat(totalMatchPercentageByUserForEveryCategory).doesNotContainKeys(peter, userWithNoAns).containsKeys(dave, jane);
		assertThat(totalMatchPercentagesByUser).doesNotContainKeys(peter, userWithNoAns).containsKeys(dave, jane);
		assertThat(movieCategory).doesNotContainKeys(peter, userWithNoAns).containsKeys(dave, jane);
		assertThat(sportsCategory).doesNotContainKeys(peter, userWithNoAns).containsKeys(dave, jane);
		assertThat(travelCategory).doesNotContainKeys(peter, userWithNoAns).containsKeys(dave, jane);
	}
	
	public void shouldThrowNoAnswersSubmittedException() throws Exception {
		//Arrange
		User userWithNoAns = new User("Tom", "Smith", "tom@yahoo.com", LocalDate.now(), Sex.MALE);
		List<User> users = Arrays.asList(peter, dave, jane, userWithNoAns);
		
		//Act
		matcher.updateAllMatches(users, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, new Matcher().new ConvertToPercent() , a -> a.booleanValue() == true? 100 : 0);
		
		Exception exc = assertThrows(NoAnswersSubmittedException.class, ()-> matcher.updateAllMatches(users, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
				, new Matcher().new ConvertToPercent() , a -> a.booleanValue() == true? 100 : 0));
		
		assertEquals("Tom has not submitted any answers so compatibility cannot be calculated.", exc.getMessage());
	}
	

	
	
	
	//@Test
	public void shouldReturnValuesInDescendingOrder() {
		//Test inner private class

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
		
		//Pre-populate map
		//preset movie answers
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
	

	
	
		

	
	
	

}
