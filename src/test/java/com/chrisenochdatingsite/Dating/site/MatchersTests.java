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

import com.chrisenochdatingsite.Dating.site.UserWithSubmittedAnswersParameterResolver.UserWithSubmittedAnswers;
import com.chrisenochdatingsite.Dating.site.UserWithSubmittedAnswersParameterResolverDavidSportsChoicesSwappedWithPeters.UserWithSubmittedAnswersDavidSportsChoicesSwappedWithPeters;
import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerImpl;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Matcher;
import com.chrisenochdatingsite.Dating.site.entity.Matcher.ConvertToPercent;
import com.chrisenochdatingsite.Dating.site.entity.MembershipType;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.util.NoAnswersSubmittedException;


@ExtendWith(UserWithSubmittedAnswersParameterResolver.class)
@ExtendWith(UserWithSubmittedAnswersParameterResolverDavidSportsChoicesSwappedWithPeters.class)
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
	
	Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero;
	
	
	@BeforeEach
	void init(@UserWithSubmittedAnswers List<User> users) {

		this.dave = users.get(0);	
		this.jane = users.get(1);
		this.peter = users.get(2);
		
		this.matcher = new Matcher();
		this.matcher.setSearchingUser(peter);
		
		//Setup for testing Map<Category, Map<Question,Map<String,Integer>>> matchPercentageByCategoryAndAnswer
		this.movies = new Category(1,  "Movies");
		this.sports = new Category(2,  "Sports");
		this.travel = new Category(3, "Travel");
		
		var horror = new AnswerImpl("Horror");
		var action =  new AnswerImpl("Action");
		var romance = new AnswerImpl("Romance");
		
		List<Answer> movieAnswerOptions = Arrays.asList(horror, action, romance);

		var basketball = new AnswerImpl("Basketball");
		var football = new AnswerImpl("Football");
		var swimming = new AnswerImpl("Swimming");
			
		List<Answer> sportsAnswerOptions = Arrays.asList(basketball, football, swimming);
		
		var hiking = 	new AnswerImpl("Hiking");
		var sightseeing =  new AnswerImpl("Sightseeing");
		var camping =  new AnswerImpl("Camping");

		List<Answer> travelAnswerOptions = Arrays.asList(hiking, sightseeing, camping);
		
		this.questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
				, movies, movieAnswerOptions);
		this.questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
				, sports, sportsAnswerOptions);
		this.questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
				, travel, travelAnswerOptions);
		
		//This must be initialised after the other variables because this method relies on the other variables having been set.
		this.prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
		

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
	
	@Test
	public void shouldReturnCorrectNumberofCategoriesInMap() {
		assertThat(matchesDave).isNotEmpty().hasSize(3);
	}
	
	
	@Test
	public void shouldReturnCorrectValuesDependingOnAnswerType() {	
		//Testing method matchPercentageByCategoryAndAnswer, which can be found in @BeforeEach
		
		//Collections gather data from the nested map returned by matchPercentageByCategoryAndAnswer(matchesDave)	
		Map<String, Object> mapInfo = extractedMapInformation(matchesDave);
		
		Set<Category> categoriesSet = (Set<Category>) mapInfo.get("categories");
		Set<Question> questionsSet = (Set<Question>) mapInfo.get("questions");
		Map<String, Integer> answersMap = (Map<String, Integer>) mapInfo.get("answers");
		
		assertThat(categoriesSet).contains(movies, sports, travel);
		assertThat(questionsSet).contains(questionMovies, questionSports, questionTravel);
		assertThat(answersMap).contains(entry("Basketball", 67), entry("Swimming", 100)
				, entry("Action", 100), entry("Horror", 0), entry("Romance", 100)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				).doesNotContainKey("Football");
	}
	
	
	
	
	@Test
	@DisplayName("Should not score omitted AnswerWeightedImpls regardless of who the searchingUser is")
	public void shouldNotScoreOmittedAnswerWeightedImpls(
			@UserWithSubmittedAnswersDavidSportsChoicesSwappedWithPeters List<User> usersSwapped) throws Exception {
		
		//Searching user is Peter. Compared user Dave has selected all 3 sports choices. Peter has omitted sportsChoicePeter1 
		//Collections gather data from the nested map returned by matchPercentageByCategoryAndAnswer (matchesDave) to be used for testing 
		Map<String, Object> mapInfo = extractedMapInformation(matchesDave);
		
		Set<Category> categoriesPeterSearching = (Set<Category>) mapInfo.get("categories");
		Set<Question> questionsPeterSearching = (Set<Question>) mapInfo.get("questions");
		Map<String, Integer> answersPeterSearching = (Map<String, Integer>) mapInfo.get("answers");
		
		//Searching user is Dave. Compared user Peter has selected all 3 sports choices. Dave has omitted sportsChoiceDave1.
		User daveSportsSwapped = usersSwapped.get(0);	
		User peterSportsSwapped = usersSwapped.get(2);
		
		Matcher matcherNew = new Matcher();
		matcherNew.setSearchingUser(daveSportsSwapped);
		
		//Reinitialise map. If don't do this, it will have the values of the previous returned map as 
		//it is pointing to the returned map in Matcher.matchPercentageByCategoryAndAnswer			
		Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero
		= createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
		
		Map<Category, Map<Question, Map<String, Integer>>> matchesComparedUserPeter = null;
		try {
			 matchesComparedUserPeter = 
					matcherNew.matchPercentageByCategoryAndAnswer(matcherNew.getSearchingUser(), peterSportsSwapped
					, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero, new Matcher().new ConvertToPercent()
					, a -> a.booleanValue() == true? 100 : 0 );
	
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
				//e.getMessage();
			}
		
		//Collections gather data from the nested map returned by matchPercentageByCategoryAndAnswer to be used for testing
		Map<String, Object> mapInfoComparedUserPeter = extractedMapInformation(matchesComparedUserPeter);
		
		Set<Category> categoriesDaveSearching = (Set<Category>) mapInfoComparedUserPeter.get("categories");
		Set<Question> questionsDaveSearching = (Set<Question>) mapInfoComparedUserPeter.get("questions");
		Map<String, Integer> answersDaveSearching = (Map<String, Integer>) mapInfoComparedUserPeter.get("answers");
		
		assertThat(categoriesPeterSearching).contains(movies, sports, travel);
		assertThat(questionsPeterSearching).contains(questionMovies, questionSports, questionTravel);
		assertThat(answersPeterSearching).contains(entry("Basketball", 67), entry("Swimming", 100)
				, entry("Action", 100), entry("Horror", 0), entry("Romance", 100)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				).doesNotContainKey("Football");
		
		assertThat(categoriesDaveSearching).contains(movies, sports, travel);
		assertThat(questionsDaveSearching).contains(questionMovies, questionSports, questionTravel);
		assertThat(answersDaveSearching).contains(entry("Basketball", 67),entry("Swimming", 100)
				, entry("Action", 100), entry("Horror", 0), entry("Romance", 100)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				).doesNotContainKey("Football");

	}
	
	@Test
	public void shouldReturnZeroWhenNoAnswersMatch() {
		//Jane and Peter (the searchingUser) have no movie answers in common
		Map<Category, Map<Question, Map<String, Integer>>> matchesJane = null;
		Map<Category, Map<Question, Map<String, Integer>>> prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero2 = createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero();
		
		//Act
		try {
			matchesJane = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), jane
					, prepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero2, new Matcher().new ConvertToPercent()
					, a -> a.booleanValue() == true? 100 : 0 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Collections gather data from the nested map matchesJane, which will be used for testing.
		Map<String, Object> mapInfoJane = extractedMapInformation(matchesJane);
	
		Set<Category> categories = (Set<Category>) mapInfoJane.get("categories");
		Set<Question> questions = (Set<Question>) mapInfoJane.get("questions");
		Map<String, Integer> answers = (Map<String, Integer>) mapInfoJane.get("answers");

		assertThat(categories).contains(movies, sports, travel);
		assertThat(questions).contains(questionMovies, questionSports, questionTravel);
		assertThat(answers).contains(entry("Action", 0), entry("Romance", 0), entry("Horror", 0));

	}
	
	@Test
	public void shouldThrowExceptionIfNoAnswersSubmitted() {
		User noSubmittedAnswersHarold = new User("Harold", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE.FEMALE, MembershipType.TRIAL);
		User noSubmittedAnswersMike = new User("Mike", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE, MembershipType.TRIAL);
		
		
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
	
	@Test
	public void shouldReturnAverage() throws Exception {
		//Arrange
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		totalPercentagesByCategoryDave.put(movies, 45);
		totalPercentagesByCategoryDave.put(sports, 55);
		totalPercentagesByCategoryDave.put(travel, 65); 
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		totalMatchPercentageByUserForEveryCategory.put(dave,totalPercentagesByCategoryDave);		
		
		//Act
		matcher.updateTotalMatchPercentagesByUser(dave, totalPercentagesByCategoryDave);
		
		//Assert - get updated field from Matcher class
		LinkedHashMap<User, Integer> totalMatchPercentagesByUserUpdated = matcher.getTotalMatchPercentagesByUser();
		assertEquals(totalMatchPercentagesByUserUpdated.get(dave), 55);
		
	}
	
	
	@Test
	public void shouldThrowExceptionIfAverageIsEmpty() throws Exception {
		//Arrange
		LinkedHashMap<Category, Integer> totalPercentagesByCategoryDave = new LinkedHashMap<>();
		Map<User, LinkedHashMap<Category, Integer>> totalMatchPercentageByUserForEveryCategory = new HashMap<>();
		totalMatchPercentageByUserForEveryCategory.put(dave,totalPercentagesByCategoryDave);
		
		//Act and assert
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
		//Arrange data to be added to totalMatchPercentagesByCategoryForEveryUser 
		LinkedHashMap<Category, Integer> totalMatchPercentageByCategoryDave = new LinkedHashMap<>();
		 totalMatchPercentageByCategoryDave.put(movies, 20);
		 totalMatchPercentageByCategoryDave.put(sports, 40);
		 totalMatchPercentageByCategoryDave.put(travel, 50);
		 
		 LinkedHashMap<Category, Integer> totalMatchPercentageByCategoryJane = new LinkedHashMap<>();
		 totalMatchPercentageByCategoryJane.put(movies, 50);
		 totalMatchPercentageByCategoryJane.put(sports, 10);
		 totalMatchPercentageByCategoryJane.put(travel, 70);
		 
		 //Arrange expected values
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
		 matcher.updateTotalMatchPercentagesByCategoryForEveryUser(dave, totalMatchPercentageByCategoryDave);
		 matcher.updateTotalMatchPercentagesByCategoryForEveryUser(jane, totalMatchPercentageByCategoryJane);
		 
		 //get the field that updateTotalMatchPercentagesByCategoryForEveryUser updates in Matcher class
		 Map<Category, LinkedHashMap<User, Integer>>totalMatchPercentagesByCategoryForEveryUser 
		 = matcher.getTotalMatchPercentagesByCategoryForEveryUser();
		 
		//get inner map to test
		 LinkedHashMap<User, Integer> moviesCategory = totalMatchPercentagesByCategoryForEveryUser.get(movies);
		 LinkedHashMap<User, Integer> sportsCategory = totalMatchPercentagesByCategoryForEveryUser.get(sports);
		 LinkedHashMap<User, Integer> travelCategory = totalMatchPercentagesByCategoryForEveryUser.get(travel);
		 
		 //Assert
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
		
		assertThat(totalMatchPercentageByUserForEveryCategory).doesNotContainKeys(peter).containsKeys(dave, jane);
		assertThat(totalMatchPercentagesByUser).doesNotContainKeys(peter).containsKeys(dave, jane);
		assertThat(movieCategory).doesNotContainKey(peter).containsKeys(dave, jane);
 		assertThat(travelCategory).doesNotContainKey(peter).containsKeys(dave, jane);
	}
	
	@Test
	@DisplayName("Should ignore any user who hasn't submitted any answers and calculate the match scores for the others.")
	public void shouldIgnoreUserIfUserHasNotSubmittedAnyAnswers() throws Exception {
		//Arrange
		User userWithNoAns = new User("Tom", "Smith", "tom@yahoo.com", LocalDate.now(), Sex.MALE, MembershipType.TRIAL);
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
	
	
	private Map<String,Object> extractedMapInformation(Map<Category, Map<Question, Map<String, Integer>>> matches) {
		Set<Category> categories = new HashSet<>();
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
		//Add values to collections ready for testing 
		for (Map.Entry<Category, Map<Question, Map<String, Integer>>> map1 : matches.entrySet()) {
			
			categories.add((Category) map1.getKey());
			
			for (Map.Entry<Question, Map<String, Integer>> map2 : map1.getValue().entrySet()) {
				
				questions.add((Question) map2.getKey());
				
				for (Map.Entry<String, Integer> map3 : map2.getValue().entrySet()) {
					
					answers.put((String)map3.getKey(), (Integer)map3.getValue());
						
				}
			}		

		}
		
		Map<String,Object> theExtractedMapInformation = new HashMap<>();
		theExtractedMapInformation.put("categories", categories);
		theExtractedMapInformation.put("questions", questions);
		theExtractedMapInformation.put("answers", answers);
		
		return theExtractedMapInformation;
	}
	
	
	private  Map<Category, Map<Question, Map<String, Integer>>> createPrepolutatedWithAllAnswerOptionsOfAnsImplsSetToZero() {
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
	

	private void debugPrintMatchScoresByCategory(Map<Category, Map<Question,Map<String,Integer>>> matchScoresByCategory) {
		for (Map.Entry<Category, Map<Question,Map<String,Integer>>> map1 : matchScoresByCategory.entrySet()) {
			System.out.println("Category: " + map1.getKey());
			
			for (Map.Entry<Question,Map<String,Integer>>map2 : map1.getValue().entrySet()) {
				System.out.println("Question: " + map2.getKey());
				
				for (Map.Entry<String,Integer> map3 : map2.getValue().entrySet()) {
					System.out.println("AnswerTxt: " + map3.getKey() + " Weight: " + map3.getValue());
						
				}
				System.out.println("------------------------------");
			}	
		}
	}
	
		

	
	
	

}
