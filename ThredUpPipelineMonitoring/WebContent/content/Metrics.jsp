<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
   import   = "com.thredUp.model.EventsMonitor,com.thredUp.model.EventsMonitorDetails"
   import ="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
   </head>
   <% 
      ArrayList arlEventsMonitor = null;
      arlEventsMonitor = (ArrayList)request.getAttribute("arlEventsMonitor");
      ArrayList arlEventsMonitorDetails = null;
      arlEventsMonitorDetails = (ArrayList)request.getAttribute("arlEventsMonitorDetails");
      %>
   <body>
      <form class="bg-primary" id="searchClient" method="post" >
      
  		
         <br><br>
         
      
         <table border=1 width="100%">
            <tr class="header">
               <th style="width:30%;">Metric Description</th>
               <th style="width:25%;">Column Name</th>
               <th style="width:35%;">Column Value</th>
           	<th style="width:10%;">Count</th>
            </tr>
            <% 
               EventsMonitor objEm=null;
				EventsMonitorDetails objEmd=null;
               	if(arlEventsMonitorDetails!=null){
               	
               	for(int i = 0 ; i < arlEventsMonitorDetails.size(); i++)
               	{
               	objEmd = (EventsMonitorDetails)arlEventsMonitorDetails.get(i);  
					objEm = objEmd.getEventsMonitor();       
                   //If details are present                   
                   if(null != objEm){
               	//Create a radio button
               %>
            <%if(objEm.isOk()) {%>
            <tr id="row<%=i+1%>" bgcolor="#00cc00">
            <%}else{ %>
            <tr id="row<%=i+1%>" bgcolor="#ff3333"><%} %>
               <td align="center"> <%=objEm.getEventsDesc() %></td>
               <td align="center"> <%=objEm.getColumnName() %></td>
               <%if(objEm.getColumnValue()!=null) {%>
               <td align="center"> <%=objEm.getColumnValue() %></td>
               <%}else{ %>
               <td align="center">-</td><%} %>
               <td align="center"> <%=objEmd.getCountOfMetric() %></td>
            </tr>
            <%}
               }
               } %>
         </table><br><br>
         
         
      </form>
   </body>
</html>