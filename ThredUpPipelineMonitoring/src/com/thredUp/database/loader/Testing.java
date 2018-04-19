package com.thredUp.database.loader;

import java.util.ArrayList;

import com.thredUp.model.PerformanceMetrics;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThredUpLoader tul = new ThredUpLoader();
		//ArrayList<PerformanceMetrics> arlLoadErrors = tul.findLoadErrors();
		/*for (PerformanceMetrics loadingErrors : arlLoadErrors) {
			System.out.println(loadingErrors.getQueryName());
		}
		ArrayList<PerformanceMetrics> arlLoadErrorsFromS3 = tul.findLoadErrorsFromS3();
		for (PerformanceMetrics loadingErrors : arlLoadErrorsFromS3) {
			System.out.println(loadingErrors.getQueryName());
		}
		
		ArrayList<PerformanceMetrics> arlQueriesWithSolutions = tul.findToBeOptimsedQueries();
		for (PerformanceMetrics loadingErrors : arlQueriesWithSolutions) {
			System.out.println(loadingErrors.getQueryName());
		}*/
		
		System.out.println("Session"+tul.checkSessionTimes());
	}

}
