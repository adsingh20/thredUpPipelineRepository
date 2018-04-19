package com.thredUp.model;

public class PerformanceMetrics {
private String queryName;
private String fileName;
private int line;
private String columnValue;
private String errorReason;
private String TypeOfError;
private String Event;
private String solution;
public String getEvent() {
	return Event;
}
public void setEvent(String event) {
	Event = event;
}
public String getSolution() {
	return solution;
}
public void setSolution(String solution) {
	this.solution = solution;
}
public String getQueryName() {
	return queryName;
}
public void setQueryName(String queryName) {
	this.queryName = queryName;
}
public String getTypeOfError() {
	return TypeOfError;
}
public void setTypeOfError(String typeOfError) {
	TypeOfError = typeOfError;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public int getLine() {
	return line;
}
public void setLine(int line) {
	this.line = line;
}
public String getColumnValue() {
	return columnValue;
}
public void setColumnValue(String columnValue) {
	this.columnValue = columnValue;
}
public String getErrorReason() {
	return errorReason;
}
public void setErrorReason(String errorReason) {
	this.errorReason = errorReason;
}
}
