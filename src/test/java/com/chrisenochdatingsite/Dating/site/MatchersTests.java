package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.entry;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Matcher;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.Matcher.ConvertToPercent;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.entity.User.Sex;
import com.chrisenochdatingsite.Dating.site.service.Answer;
import com.chrisenochdatingsite.Dating.site.service.Question;


@ExtendWith(UserWithSubmittedAnswersParameterResolver.class)
public class MatchersTests {
	
	private User dave;
	private User peter;
	private User jane;
	
	private Matcher matcher;
	
	private Map<Category, Map<Question, Map<String, Integer>>> matchesDave;
	
	@BeforeEach
	void init(Map<String, User> users) {

	this.dave = users.get("Dave");
	this.peter = users.get("Peter");
	this.jane = users.get("Jane");
	
	this.matcher = new Matcher();
	this.matcher.setSearchingUser(peter);
	
	try {
		this.matchesDave = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), dave, new Matcher().new ConvertToPercent()
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
	
	
	public void shouldReturnCorrectNumberofCategoriesInMap() {
		assertThat(matchesDave).isNotEmpty().hasSize(3);
	}
	
	
	//Test use case
	@Test
	public void shouldReturnCorrectValuesDependingOnAnswerType() {

		Set<Category> categories = new HashSet<>();
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
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
		
		Category movies = new Category(1, "Movies");
		Category sports = new Category(2, "Sports");
		Category travel = new Category(3, "Travel");
		
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
		
		var questionMovies = new QuestionWithOptionsImpl(1, "Please indicate how much you like the following movie genres."
				, movieAnswerOptions, movies);
		var questionSports = new QuestionWithOptionsImpl(2, "Please indicate how much you like the following sport."
				, sportsAnswerOptions, sports);
		var questionTravel = new QuestionWithOptionsImpl(3, "Please indicate how much you like the following type of travel."
				, travelAnswerOptions, travel);
		
		assertThat(categories).contains(movies, sports, travel);
		assertThat(questions).contains(questionMovies, questionSports, questionTravel);
		assertThat(answers).contains(entry("Basketball", 67), entry("Football", 67), entry("Swimming", 100)
				, entry("Action", 100), entry("Horror", 100), entry("Romance", 100)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				);
		
		System.out.println(categories);
		System.out.println(questions);
		System.out.println(answers);
		
		
	}
	
	
	
	//Test when no answers match
	
	//Test with SubmittedAnswerSingleImpl

		
	@Test
	public void shouldThrowExceptionIfNoAnswersSubmitted() {
		User noSubmittedAnswers = new User("Harold", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
		
		Exception exc = assertThrows(Exception.class, ()-> matcher.matchPercentageByCategoryAndAnswer(noSubmittedAnswers, dave, new Matcher().new ConvertToPercent()
				, a -> a.booleanValue() == true? 100 : 0 ));
		
		assertEquals("Harold has not submitted any answers so compatibility cannot be calculated.", exc.getMessage());
		
		User noSubmittedAnswers2 = new User("Mike", "Smith", "harold@yahoo.com", LocalDate.of(1983,  9,  23), Sex.MALE);
		
		Exception exc2 = assertThrows(Exception.class, ()-> matcher.matchPercentageByCategoryAndAnswer(peter, noSubmittedAnswers2, new Matcher().new ConvertToPercent()
				, a -> a.booleanValue() == true? 100 : 0 ));
		
		assertEquals("Mike has not submitted any answers so compatibility cannot be calculated.", exc2.getMessage());
		
	}
	
	
	//@Test
	public void shouldReturnValuesInDescendingOrder() {
		//Test inner private class

	}

	
	@Test
	public void checkParameterResolverIsWorking() {
		assertEquals("Dave", dave.getFirstName());
		assertEquals(3, dave.getSubmittedAnswers().size());
		assertThat(matchesDave).isNotEmpty();
		assertEquals(3, matchesDave.size());
		
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
		
	}
		

	
	
	

}
