package com.ration.predicament;

import java.util.Arrays;

import com.ration.predicament.service.RationPredicamentService;

public class RationPredicamentApp {
	
	public static void main(String args[]) {
		RationPredicamentService service = new RationPredicamentService();
		service.allocate(123,Arrays.asList(0.5, 1, 2, 5), Arrays.asList(50,50,50,30), Arrays.asList(10,10,10,10), Arrays.asList(63, 125, 40, 20));
		service.allocate(289,Arrays.asList(0.5, 1, 2, 5), Arrays.asList(50,50,50,20), Arrays.asList(10,10,10,10), Arrays.asList(60, 65, 55, 20));
	}

}
