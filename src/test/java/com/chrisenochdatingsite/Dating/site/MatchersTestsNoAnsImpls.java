package com.chrisenochdatingsite.Dating.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.chrisenochdatingsite.Dating.site.entity.Answer;
import com.chrisenochdatingsite.Dating.site.entity.AnswerWeightedImpl;
import com.chrisenochdatingsite.Dating.site.entity.Category;
import com.chrisenochdatingsite.Dating.site.entity.Matcher;
import com.chrisenochdatingsite.Dating.site.entity.Question;
import com.chrisenochdatingsite.Dating.site.entity.QuestionWithOptionsImpl;
import com.chrisenochdatingsite.Dating.site.entity.User;
import com.chrisenochdatingsite.Dating.site.interfaces.SubmittedAnswer;


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
	void init(List<User> usersNoAnsImpls) {
		
		this.daveNoAnsImpls = usersNoAnsImpls.get(0);
		this.janeNoAnsImpls = usersNoAnsImpls.get(1);
		this.peterNoAnsImpls = usersNoAnsImpls.get(2);

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
		
		List<Answer> movieAnswerOptions = Arrays.asList(horror, action, romance);

		var basketball = new AnswerWeightedImpl("Basketball");
		var football = new AnswerWeightedImpl("Football");
		var swimming = new AnswerWeightedImpl("Swimming");
			
		List<Answer> sportsAnswerOptions = Arrays.asList(basketball, football, swimming);
		
		var hiking = 	new AnswerWeightedImpl("Hiking");
		var sightseeing =  new AnswerWeightedImpl("Sightseeing");
		var camping =  new AnswerWeightedImpl("Camping");

		List<Answer> travelAnswerOptions = Arrays.asList(hiking, sightseeing, camping);
				
		
		this.questionMovies = new QuestionWithOptionsImpl("Please indicate how much you like the following movie genres."
				,movies, movieAnswerOptions);
		this.questionSports = new QuestionWithOptionsImpl("Please indicate how much you like the following sport."
				 ,sports, sportsAnswerOptions);
		this.questionTravel = new QuestionWithOptionsImpl("Please indicate how much you like the following type of travel."
				,travel, travelAnswerOptions);
		
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
		
		for (Map.Entry<Category, Map<Question, Map<String, Integer>>> map1 : matchesDaveNoAnsImpls.entrySet()) {
			
			categories.add((Category) map1.getKey());
			
			for (Map.Entry<Question, Map<String, Integer>> map2 :  map1.getValue().entrySet()) {
				
				questions.add((Question) map2.getKey());
				
				for (Map.Entry<String, Integer> map3 : map2.getValue().entrySet()) {
					
					answers.put((String)map3.getKey(), (Integer)map3.getValue());
						
				}
			}		
	
		}
			
		assertThat(categories).contains(movies, sports, travel);
		assertThat(questions).contains(questionMovies, questionSports, questionTravel);
		assertThat(answers).contains(entry("Basketball", 67), entry("Football", 67), entry("Swimming", 100)
				, entry("Action", 67), entry("Romance", 50)
				, entry("Sightseeing", 17), entry("Camping", 34), entry("Hiking", 67)
				).doesNotContainKey("Horror");	

	}

}
