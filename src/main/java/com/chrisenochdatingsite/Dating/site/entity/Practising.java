package com.chrisenochdatingsite.Dating.site.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practising {
	
	public static void main(String[] args) {
		
		Map<String, Object> test = test2();
			
		Set<Category> categories = (Set<Category>) test.get("cat");
		System.out.println(categories);
		
	}
		
	
	public static Map<String, Object> test2(){
		Category cat = new Category("sports");
		Set<Category> categories = new HashSet<>();
		categories.add(cat);
		
		Set<Question> questions = new HashSet<>();
		Map<String, Integer> answers = new HashMap<>();
		
		Map<String, Object> test = new HashMap<>();
		
		test.put("cat", categories);
		test.put("q", questions);
		test.put("a", answers);
		
//		Set<Category> categoriesNew = (Set<Category>) test.get("cat");
//		System.out.println(categoriesNew);
		
		return test;
	}

		

}
