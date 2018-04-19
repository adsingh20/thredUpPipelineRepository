package com.thredUp.model;

import java.sql.Date;

public class EventsMonitorDetails {
	private EventsMonitor eventsMonitor;
	Date capturedDate;
	int countOfMetric;
	public EventsMonitor getEventsMonitor() {
		return eventsMonitor;
	}
	public void setEventsMonitor(EventsMonitor eventsMonitor) {
		this.eventsMonitor = eventsMonitor;
	}
	public Date getCapturedDate() {
		return capturedDate;
	}
	public void setCapturedDate(Date capturedDate) {
		this.capturedDate = capturedDate;
	}
	public int getCountOfMetric() {
		return countOfMetric;
	}
	public void setCountOfMetric(int countOfMetric) {
		this.countOfMetric = countOfMetric;
	}
	
	
}
