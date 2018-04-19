package com.thredUp.database.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.thredUp.model.EventsMonitor;
import com.thredUp.model.LoadEvents;
import com.thredUp.model.PerformanceMetrics;
import com.thredUp.common.util.DbUtil;



public class ThredUpLoader {
	private Connection dbConnection;
	
	
	
	public  int findByColumnName(String columnName) {
    	int count=0;	
    	try {
			dbConnection = DbUtil.getConnection();
			String query = "select count(" + columnName +") from events_test where TRUNC(event_time)=TRUNC(sysdate) and " + columnName + " is not null and " + columnName + " !=''";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
					
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(query+result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
    
    public  int findByColumnValues(String columnName, String columnValue) {
		int count=0;
    	try {
			dbConnection = DbUtil.getConnection();
			String query = "select count(*) from events_test where " + columnName +" = ? and TRUNC(event_time)=TRUNC(sysdate)";
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			prepStatement.setString(1, columnValue);			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
    
    public  ArrayList<EventsMonitor> findInitialMetrics() {
    	EventsMonitor em = new EventsMonitor();
    	ArrayList<EventsMonitor> arleventsMonitor = new ArrayList<EventsMonitor>();
    	try {
			dbConnection = DbUtil.getConnection();
			
			String query ="select monitorid,eventsdesc,columnname,columnvalue,isColumnname,levelid,parentid,isWeekly,isWeeklyAverage,baselinepercentage from events_monitoring where obsolete is false;";
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			//prepStatement.setString(1, columnValue);			
			
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()){
				em = new EventsMonitor();
	              //Retrieve two columns.
				em.setMonitorId(rs.getInt(1));
				em.setEventsDesc(rs.getString(2));
	            em.setColumnName(rs.getString(3)); 
	            em.setColumnValue(rs.getString(4));
	            em.setIsColumnName(rs.getBoolean(5));
	            em.setLevelId(rs.getInt(6));
	            em.setParentId(rs.getInt(7));
	            em.setWeekly(rs.getBoolean(8));
	            em.setWeeklyAverage(rs.getBoolean(9));
	            em.setPercentage(rs.getFloat(10)/100);
	            arleventsMonitor.add(em);
	           
	        }			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	for (EventsMonitor eventsMonitor : arleventsMonitor) {
			System.out.println("1"+eventsMonitor.getColumnName()+eventsMonitor.getColumnValue());
		}
    	
		return arleventsMonitor;
	}
    
    public  boolean insertMetrics(int count,EventsMonitor em) {
    	boolean result=false;
		dbConnection = DbUtil.getConnection();
		PreparedStatement prepStatement;
		try {
			System.out.println("count"+count);
			
			prepStatement = dbConnection.prepareStatement("INSERT INTO events_monitoring_details (eventsid,capturedts,countofmetric,modifiedts,obsolete) values (?,sysdate,?,sysdate,false)");
			prepStatement.setInt(1, em.getMonitorId());
			prepStatement.setInt(2, count);
			
			int updatedRows =  prepStatement.executeUpdate();
			if(updatedRows>0) {
				System.out.println("Row is Inserted");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
    
    
    public int findAverageOfWeek(int monitorId) {
    	int count=0;
    	try {
			dbConnection = DbUtil.getConnection();
			String query  ="select AVG(countofmetric) from events_monitoring_details where eventsid=? and trunc(capturedts)>=trunc(sysdate-7) and trunc(capturedts)>trunc(sysdate) group by trunc(capturedts)";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			prepStatement.setInt(1, monitorId);			
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
    
    public  int findCountOfLastfWeek(int monitorId) {
    	int count=0;
    	try {
			dbConnection = DbUtil.getConnection();
			String query  ="select countofmetric from events_monitoring_details where eventsid=? and trunc(capturedts)=trunc(sysdate-7)";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			prepStatement.setInt(1, monitorId);		
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
    
    public  int findCountOfToday(int monitorId) {
    	int count=0;
    	try {
			dbConnection = DbUtil.getConnection();
			String query  ="select countofmetric from events_monitoring_details where eventsid=? and trunc(capturedts)=trunc(sysdate)";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			prepStatement.setInt(1, monitorId);		
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
    
    public  int findCountOfYesterday(int monitorId) {
    	int count=0;
    	try {
			dbConnection = DbUtil.getConnection();
			String query  ="select countofmetric from events_monitoring_details where eventsid=? and trunc(capturedts)=trunc(sysdate-1)";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
			prepStatement.setInt(1, monitorId);		
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					System.out.println(result.getInt(1));
					count=result.getInt(1);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
    public ArrayList<PerformanceMetrics> findLoadErrors() {
    	PerformanceMetrics le = new PerformanceMetrics();
    	ArrayList<PerformanceMetrics> arlLE = new ArrayList<PerformanceMetrics>();
    	try {
    		dbConnection = DbUtil.getConnection();
			String query  ="select sq.querytxt, substring(d.filename,14,40), \n" + 
					" d.line_number as line, \n" + 
					" substring(d.value,1,16) as value,\n" + 
					" substring(le.err_reason,1,48) as err_reason\n" + 
					" from stl_loaderror_detail d, stl_load_errors le,STL_QUERY sq\n" + 
					" where d.query = le.query and TRUNC(le.starttime)>=TRUNC(sysdate-14) and sq.query=d.query";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
				
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					le= new PerformanceMetrics();
					le.setColumnValue(result.getString(4));
					le.setErrorReason(result.getString(5));
					le.setFileName(result.getString(2));
					le.setQueryName(result.getString(1));
					le.setLine(result.getInt(3));
					le.setTypeOfError("Loading Errors");
					arlLE.add(le);
				}				
			}			
    	}catch(Exception e) {
    		
    	}
		return arlLE;
    }
    
    public ArrayList<PerformanceMetrics> findLoadErrorsFromS3() {
    	PerformanceMetrics le = new PerformanceMetrics();
    	ArrayList<PerformanceMetrics> arlLE = new ArrayList<PerformanceMetrics>();
    	try {
    		dbConnection = DbUtil.getConnection();
			String query  = "select sq.querytxt, substring(key from 1 for 20) as file, \n" + 
					"substring(error from 1 for 102) as error from stl_s3client_error sse,STL_QUERY sq where sq.query=sse.query ";
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
				
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					le= new PerformanceMetrics();			
					le.setErrorReason(result.getString(3));
					le.setFileName(result.getString(2));
					le.setQueryName(result.getString(1));
					le.setTypeOfError("Loading Errors from S3");
					arlLE.add(le);
				}				
			}			
    	}catch(Exception e) {
    		
    	}
		return arlLE;
    }
    
    public ArrayList<PerformanceMetrics> findToBeOptimsedQueries() {
    	PerformanceMetrics le = new PerformanceMetrics();
    	ArrayList<PerformanceMetrics> arlLE = new ArrayList<PerformanceMetrics>();
    	try {
    		dbConnection = DbUtil.getConnection();
    		String query  ="SELECT querytxt,  event, \n" + 
					" solution, \n" + 
					"trim(event_time) as event_time from stl_alert_event_log sael, "
					+ "STL_QUERY sq where sq.query=sael.query and querytxt like 'select%events%' " + 
					" order by event_time desc";
			
			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
			System.out.println(query);
			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
				
			
			ResultSet result = prepStatement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					
					le= new PerformanceMetrics();
					le.setSolution(result.getString(3));
					le.setEvent(result.getString(2));
					le.setQueryName(result.getString(1));
					le.setTypeOfError("Unoptimised Queries");
					arlLE.add(le);
				}				
			}			
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return arlLE;
    }
    
    
    
    public HashMap<Long,LoadEvents> findLoadRuntimes() {
        HashMap<Long,LoadEvents> runtime = new HashMap<Long,LoadEvents>();
        LoadEvents le = new LoadEvents();
        	try {
        		dbConnection = DbUtil.getConnection();
        		String query  ="select  t.xid,TRUNC(starttime),date_diff('sec',min(starttime),max(endtime)) from svl_statementtext t where \n" + 
        				"(t.text LIKE 'COMMIT%' OR  \n" + 
        				"text LIKE 'padb_fetch_sample%' OR  \n" + 
        				"t.text LIKE 'copy%' or \n" + 
        				"t.text LIKE 'Analyze%'  OR \n" + 
        				"t.text LIKE 'analyze compression phase%') AND \n" + 
        				"t.xid In (select sqt.xid from STL_QUERYTEXT sqt where sqt.text like 'copy%' )\n" + 
        				"group by t.xid,TRUNC(starttime);";
    			
    			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
    			System.out.println(query);
    			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
    				
    			
    			ResultSet result = prepStatement.executeQuery();
    			
    			if (result != null) {
    				while (result.next()) {
    					le = new LoadEvents();
    					le.setFirstLoadTime(result.getDate(2));
    					le.setRuntime(result.getLong(3));
    					runtime.put(result.getLong(1),le);
    				}				
    			}			
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    		return runtime;
        } 
    
    public boolean checkSessionTimes() {
        
        	try {
        		dbConnection = DbUtil.getConnection();
        		String query  ="select max(session_id),min(session_id) from events where session_id !=''";
    			
    			//String query = "select count(*) from events_test where " + columnName +"= '" + columnValue + "'" + "and TRUNC(event_time)=TRUNC(sysdate)";
    			System.out.println(query);
    			PreparedStatement prepStatement = dbConnection.prepareStatement(query);
    				
    			
    			ResultSet result = prepStatement.executeQuery();
    			
    			if (result != null) {
    				while (result.next()) {
    					String maximum = result.getString(1);
    					String minimum = result.getString(2);
    					Date maxDate = new Date(Long.parseLong(maximum));
    					Date minDate = new Date(Long.parseLong(minimum));
    					Date currentDate = new Date();
    					Calendar c = Calendar.getInstance();
    			        c.setTime(currentDate);
    			        
    			        System.out.println((currentDate.getTime()-maxDate.getTime())/ (24 * 60 * 60 * 1000));
    			        c.add(Calendar.DATE, 117);
    			        
    			        if(c.getTime().before(minDate) && c.getTime().after(maxDate)) {
			        		return true;
    			        }
    			        

    				}				
    			}			
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    		return false;
        } 
}
