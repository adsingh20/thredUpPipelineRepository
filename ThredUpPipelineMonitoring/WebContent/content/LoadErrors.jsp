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
   	 arlLoadErrors = (ArrayList)request.getAttribute("arlLoadErrors");
   	
      %>
   <body>
      <form class="bg-primary" id="searchClient" method="post" name="searchClient" action="<%=request.getContextPath()%>/NeuroIDController">
      
  		<h1>Loading Errors</h1>
         <br><br>
         <% 
               PerformanceMetrics objPm=null;
               	if(arlLoadErrors!=null){
               	%>
               	 <table border=1 width="100%">
            <tr class="header">
               <th style="width:20%;">Column Name</th>
               <th style="width:20%;">Error reason</th>
               <th style="width:20%;">File Name</th>
               <th style="width:20%;">Query</th>
           	   <th style="width:20%;">Line</th>
            </tr>  
               	<% 
               	for(int i = 0 ; i < arlLoadErrors.size(); i++)
               	{
               	objPm = (PerformanceMetrics)arlLoadErrors.get(i);         
                   //If details are present                   
                   
               	//Create a radio button
               %>
      
                  
            <tr id="row<%=i+1%>">
               <td align="center"> <%=objPm.getColumnValue() %></td>
               <td align="center"> <%=objPm.getErrorReason()%></td>
               <td align="center"> <%=objPm.getFileName() %></td>
               <td align="center"> <%=objPm.getQueryName() %></td>
                <td align="center"> <%=objPm.getLine() %></td>
            </tr>
            <%
               }%>
              
         </table><%} %>
         <br><br>
        
         
      </form>
   </body>
</html>