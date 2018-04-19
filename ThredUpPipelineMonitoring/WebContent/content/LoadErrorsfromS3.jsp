<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
   import   = "com.thredUp.model.PerformanceMetrics"
   import ="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
   </head>
   <% 
      ArrayList arlLoadErrors = null;
   ArrayList arlLoadErrorsFromS3 = null;
   ArrayList arlToBeOptimisedQueries = null;
   	 
   	arlLoadErrorsFromS3 = (ArrayList)request.getAttribute("arlLoadErrorsFromS3");
   	System.out.println("Size 5"+arlLoadErrorsFromS3.size());
   	
      %>
   <body>
      <form class="bg-primary" id="searchClient" method="post" name="searchClient" action="<%=request.getContextPath()%>/NeuroIDController">
      
  		
         <center><h1>Loading Errors from S3</h1></center>
         <% if(arlLoadErrorsFromS3!=null){
        	 PerformanceMetrics objPm1= new  PerformanceMetrics();%>
               	 <table border=1 width="100%">
            <tr class="header">
               
               <th style="width:40%;">Error reason</th>
               <th style="width:20%;">File Name</th>
               <th style="width:40%;">Query</th>
           
            </tr>  
               	<% 
               	for(int i = 0 ; i < arlLoadErrorsFromS3.size(); i++)
               	{
               		objPm1 = (PerformanceMetrics)arlLoadErrorsFromS3.get(i);         
                   //If details are present                   
                   
               	//Create a radio button
               %>
      
                  
            <tr id="row<%=i+1%>">
               
               <td align="center"> <%=objPm1.getErrorReason()%></td>
               <td align="center"> <%=objPm1.getFileName() %></td>
               <td align="center"> <%=objPm1.getQueryName() %></td>
            
            </tr>
            <%
               }%>
              
         </table><%} %>
         <br><br>
        
         
      </form>
   </body>
</html>