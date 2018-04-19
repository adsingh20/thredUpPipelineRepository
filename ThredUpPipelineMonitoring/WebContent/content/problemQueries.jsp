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
     
   ArrayList arlToBeOptimisedQueries = null;
 
   	arlToBeOptimisedQueries = (ArrayList)request.getAttribute("arlToBeOptimisedQueries");
   	
      %>
   <body>
      <form class="bg-primary" id="searchClient" method="post" name="searchClient" action="<%=request.getContextPath()%>/NeuroIDController">
     
  		
         <br><br>
         <center><h1>Problematic Queries</h1></center>
         <% 
         PerformanceMetrics objPm2 = new  PerformanceMetrics() ;
               	if(arlToBeOptimisedQueries!=null){
               	%>
               	 <table border=1 width="100%">
            <tr class="header">
               <th style="width:30%;">Query</th>
                <th style="width:10%;">Event</th>
           	   <th style="width:60%;">Solution</th>
            </tr>  
               	<% 
               	for(int i = 0 ; i < arlToBeOptimisedQueries.size(); i++)
               	{
               		objPm2 = (PerformanceMetrics)arlToBeOptimisedQueries.get(i);         
                  
               %>
      
                  
            <tr id="row<%=i+1%>">
               <td align="center"> <%=objPm2.getQueryName() %></td>
               <td align="center"> <%=objPm2.getEvent()%></td>
               <td align="center"> <%=objPm2.getSolution() %></td>
             
            </tr>
            <%
               }%>
              
         </table><%} %>
         
      </form>
   </body>
</html>