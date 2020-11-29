package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
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


@ExtendWith( UserWithSubmittedAnswers_NoAnsImpls_ParameterResolver.class)
public class MatchersTestsNoAnsImpls {
	

	private User daveNoAnsImpls;
	private User janeNoAnsImpls;
	private User peterNoAnsImpls;
	
	private Matcher matcher;
	private Map<Category, Map<Question, Map<String, Integer>>> matchesDaveNoAnsImpls;
	
	private Category movies;
	private Category sports;
	private Category travel;
	
	private Question questionMovies;
	private Question questionSports;
	private Question questionTravel;
	
	
	@BeforeEach
	void init(Map<String, User> usersNoAnsImpls) {
		
		this.daveNoAnsImpls = usersNoAnsImpls.get("Dave");
		this.janeNoAnsImpls = usersNoAnsImpls.get("Jane");
		this.peterNoAnsImpls = usersNoAnsImpls.get("Peter");
		
		Map<Category, Map<Question, Map<String, Integer>>> emptyMap = new HashMap<>();
		
		this.matcher = new Matcher();
		matcher.setSearchingUser(peterNoAnsImpls);

		try {

				 this.matchesDaveNoAnsImpls = matcher.matchPercentageByCategoryAndAnswer(matcher.getSearchingUser(), daveNoAnsImpls
							, emptyMap, new Matcher().new ConvertToPercent()
							, a -> a.booleanValue() == true? 100 : 0 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//Test when empty map passed at first
	@Test
	public void shouldReturnThreeEntriesWhenEmptyMapPassed() {

		assertThat(matchesDaveNoAnsImpls).isNotEmpty().hasSize(3).containsKeys(movies, sports, travel);
		
	}
	
	@Test
	public void shouldReturnCorrectValues() {	
	
		Set<Category> categories = new HashSet<>();
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
		for (Map.Entry map1 : matchesDaveNoAnsImpls.entrySet()) {
			
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
				, entry("Action", 67), entry("Romance", 50)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				).doesNotContainKey("Horror");
		
		System.out.println(categories);
		System.out.println(questions);
		System.out.println(answers);

	}

}
