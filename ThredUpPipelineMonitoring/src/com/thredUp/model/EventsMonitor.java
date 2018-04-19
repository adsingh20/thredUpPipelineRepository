package com.thredUp.model;

public class EventsMonitor {
private int monitorId;
private String eventsDesc;
private String columnName;
private String columnValue;
private boolean isColumnName;
private int levelId;
private boolean isOk;
private int parentId;
private boolean isWeekly;
private boolean isWeeklyAverage;
private float baselinePercentage;
public boolean isWeekly() {
	return isWeekly;
}
public void setWeekly(boolean isWeekly) {
	this.isWeekly = isWeekly;
}
public boolean isWeeklyAverage() {
	return isWeeklyAverage;
}
public void setWeeklyAverage(boolean isWeeklyAverage) {
	this.isWeeklyAverage = isWeeklyAverage;
}
public float getPercentage() {
	return baselinePercentage;
}
public void setPercentage(float percentage) {
	this.baselinePercentage = percentage;
}
public int getParentId() {
	return parentId;
}
public void setParentId(int parentId) {
	this.parentId = parentId;
}
public int getMonitorId() {
	return monitorId;
}
public boolean isOk() {
	return isOk;
}
public void setOk(boolean isOk) {
	this.isOk = isOk;
}
public void setMonitorId(int monitorId) {
	this.monitorId = monitorId;
}
public String getEventsDesc() {
	return eventsDesc;
}
public void setEventsDesc(String eventsDesc) {
	this.eventsDesc = eventsDesc;
}
public String getColumnName() {
	return columnName;
}
public void setColumnName(String columnName) {
	this.columnName = columnName;
}
public String getColumnValue() {
	return columnValue;
}
public void setColumnValue(String columnValue) {
	this.columnValue = columnValue;
}
public boolean isColumnName() {
	return isColumnName;
}
public void setIsColumnName(boolean isColumnName) {
	this.isColumnName = isColumnName;
}
public int getLevelId() {
	return levelId;
}
public void setLevelId(int levelId) {
	this.levelId = levelId;
}
}
