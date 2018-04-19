package com.thredUp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

import com.thredUp.model.EventsMonitor;
import com.thredUp.model.EventsMonitorDetails;
import com.thredUp.model.PerformanceMetrics;
import com.thredUp.database.loader.ThredUpLoader;


/**
 * Servlet implementation class NeuroIdController
 */

@SuppressWarnings("serial")
public class ThredUpController extends HttpServlet {
	private ThredUpLoader thredUpLoader;
	private static String METRIC = "content/Metrics.jsp";
	private static String PERFORMANCE = "content/Performance.jsp";
	private static String OTHER_METRIC = "content/OtherMetrics.jsp";
	private static String PERFORM_METRIC = "content/PerformanceMetrics.jsp";
	private static String LOAD_ERRORS = "content/LoadErrors.jsp";
	private static String LOAD_ERRORS_FROMS3 = "content/LoadErrorsfromS3.jsp";
	private static String LOAD_QUERIES = "content/problemQueries.jsp";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = null;
		thredUpLoader = new ThredUpLoader();
		ArrayList<EventsMonitor> arlEventsMonitor = new ArrayList<EventsMonitor>();
		ArrayList<EventsMonitorDetails> arlEventsMonitorDetails = new ArrayList<EventsMonitorDetails>();
		EventsMonitorDetails emd = null;
		action = request.getParameter("action");
		String forward = "";	
		boolean isOk=false;
		System.out.println("pageName"+action);
		if(action.equalsIgnoreCase("metric")) {
			ArrayList<EventsMonitor> arlEm = thredUpLoader.findInitialMetrics();
		    for (EventsMonitor eventsMonitor : arlEm) {
		    isOk = false; 
		    	if(eventsMonitor.isColumnName()) {
		    		System.out.println("em.getColumnName()"+eventsMonitor.getColumnName());
		    		System.out.println("em.getColumnValue()"+eventsMonitor.getColumnValue());
		    		int count = thredUpLoader.findByColumnName(eventsMonitor.getColumnName());
		    		thredUpLoader.insertMetrics(count,eventsMonitor);
		    	}
		 
		    	else {
		    		System.out.println("em.getColumnName()"+eventsMonitor.getColumnName());
		    		System.out.println("em.getColumnValue()"+eventsMonitor.getColumnValue());
		    		if(eventsMonitor.getColumnValue()!=null) {
		    			thredUpLoader.insertMetrics(thredUpLoader.findByColumnValues(eventsMonitor.getColumnName(), eventsMonitor.getColumnValue()),eventsMonitor);}
		    	}
		    
			
					if(eventsMonitor.getLevelId()==0) {
						
						int averageOfThisWeek=-1;
						int countLastWeek=-1;
						int countYesterday=-1;
						if(eventsMonitor.isWeekly()) {
							System.out.println("eventsMonitor.isWeekly()");
						 averageOfThisWeek =thredUpLoader.findAverageOfWeek(eventsMonitor.getMonitorId());}
						if(eventsMonitor.isWeeklyAverage()) {
							System.out.println("eventsMonitor.isWeeklyAverage()");
						countLastWeek =thredUpLoader.findCountOfLastfWeek(eventsMonitor.getMonitorId());}
						int countToday =thredUpLoader.findCountOfToday(eventsMonitor.getMonitorId());
						 countYesterday = thredUpLoader.findCountOfYesterday(eventsMonitor.getMonitorId());
						 System.out.println("1+eventsMonitor.getPercentage())*countLastWeek"+(1+eventsMonitor.getPercentage())*countLastWeek);
						if(countToday==0 && countYesterday==0) {
							isOk=false;
						}else if(countToday<(1+eventsMonitor.getPercentage())*countLastWeek && countToday>(1-eventsMonitor.getPercentage())*countLastWeek && averageOfThisWeek!=-1){
							System.out.println("Rtrue 1");
							isOk=true;
						}else if(countToday<(1+eventsMonitor.getPercentage())*averageOfThisWeek && countToday>(1-eventsMonitor.getPercentage())*averageOfThisWeek && countLastWeek!=-1){
							System.out.println("Rtrue 2");
							isOk=true;
						}else if(countToday<(1+eventsMonitor.getPercentage())*countYesterday && countToday>(1-eventsMonitor.getPercentage())*countYesterday && countYesterday!=-1){
							System.out.println("Rtrue 3");
							isOk=true;
						}
						else {
							isOk=false;
						}
					}else {
						int averageOfThisWeek=-1;
						int countLastWeek=-1;
						int countYesterday = -1; 
						if(eventsMonitor.isWeekly()) {
						 averageOfThisWeek =thredUpLoader.findAverageOfWeek(eventsMonitor.getMonitorId());
						System.out.println("averageOfThisWeek"+averageOfThisWeek);}
						if(eventsMonitor.isWeeklyAverage()) {
						 countLastWeek =thredUpLoader.findCountOfLastfWeek(eventsMonitor.getMonitorId());
						System.out.println("countLastWeek"+countLastWeek);}
						int countToday =thredUpLoader.findCountOfToday(eventsMonitor.getMonitorId());
						countYesterday = thredUpLoader.findCountOfYesterday(eventsMonitor.getMonitorId());
						System.out.println("countToday"+countToday);
						boolean isOk2 = false;
						if(countToday==0 && countYesterday==0) {
							isOk=false;
						}else if(countToday<(1+eventsMonitor.getPercentage())*countLastWeek && countToday>(1-eventsMonitor.getPercentage())*countLastWeek && averageOfThisWeek!=-1){
							System.out.println("Rtrue 1");
							isOk=true;
						}else if(countToday<(1+eventsMonitor.getPercentage())*averageOfThisWeek && countToday>(1-eventsMonitor.getPercentage())*averageOfThisWeek && countLastWeek!=-1){
							System.out.println("Rtrue 2");
							isOk=true;
						}else if(countToday<(1+eventsMonitor.getPercentage())*countYesterday && countToday>(1-eventsMonitor.getPercentage())*countYesterday && countYesterday!=-1){
							System.out.println("Rtrue 3");
							isOk=true;
						}
						else {
							isOk=false;
						}
						if(eventsMonitor.getParentId()!=0){
						int parentCountLastWeek =thredUpLoader.findCountOfLastfWeek(eventsMonitor.getParentId());
						int parentCountToday =thredUpLoader.findCountOfToday(eventsMonitor.getParentId());
						try {
						if(countToday/parentCountToday<(1+eventsMonitor.getPercentage())*countLastWeek/parentCountLastWeek || countToday/parentCountToday>(1-eventsMonitor.getPercentage())*countLastWeek/parentCountLastWeek) {
							isOk2=false;
						}else {
							isOk2=true;
						}
						if(isOk2 && isOk) {
							isOk=true;
						}else {
							isOk=false;
						}
						}catch(Exception e) {
							System.out.println("Exception");
						}
						}
						
					}
					eventsMonitor.setOk(isOk);
					emd = new EventsMonitorDetails();
					emd.setEventsMonitor(eventsMonitor);
					emd.setCountOfMetric(thredUpLoader.findCountOfToday(eventsMonitor.getMonitorId()));
					arlEventsMonitor.add(eventsMonitor);
					arlEventsMonitorDetails.add(emd);
					request.setAttribute("arlEventsMonitor", arlEventsMonitor);
					request.setAttribute("arlEventsMonitorDetails", arlEventsMonitorDetails);
		    }
		    
			forward = METRIC;
		}else if(action.equalsIgnoreCase("performance")){
			forward = PERFORM_METRIC;
			}
		else if(action.equalsIgnoreCase("otherMetrics")){
			request.setAttribute("runtime", thredUpLoader.findLoadRuntimes());
			forward = OTHER_METRIC;
		}else if(action.equalsIgnoreCase("loading")){
			request.setAttribute("arlLoadErrors", thredUpLoader.findLoadErrors());
			System.out.println("Size 1"+thredUpLoader.findLoadErrors().size());
			forward = LOAD_ERRORS;
		}
		else if(action.equalsIgnoreCase("copying")){
			request.setAttribute("arlLoadErrorsFromS3", thredUpLoader.findLoadErrorsFromS3());
			forward = LOAD_ERRORS_FROMS3;
		}
		else if(action.equalsIgnoreCase("queries")){
			request.setAttribute("arlToBeOptimisedQueries", thredUpLoader.findToBeOptimsedQueries());
			forward = LOAD_QUERIES;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
