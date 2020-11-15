package com.chrisenochdatingsite.Dating.site.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.chrisenochdatingsite.Dating.site.service.SubmittedAnswer;

public class Nested_GroupByPracticeStreams {

	
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
	
}
