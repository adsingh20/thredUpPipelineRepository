<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
   import   = "com.thredUp.model.LoadEvents"
   import ="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
   </head>
   <% 
      HashMap<Long,LoadEvents> runtime = null;
   	  runtime = (HashMap)request.getAttribute("runtime");
      
      %>
   <body>
      <form class="bg-primary" id="otherMetrics" method="post" name="otherMetrics">
      <p align="right">
      
       </p> 
  		
         <br><br>
         <h1>Loading Metrics</h1>
      
         <table id="clientTable" width="100%" border=1>
            <tr class="header">
               <th style="width:40%;">Load Key</th>
               <th style="width:25%;">Load Time</th>
               <th style="width:35%;">Running Time(seconds)</th>
           
            </tr>
            <% 
            	LoadEvents objLE=null;
			for (Long key : runtime.keySet()) {
               	objLE=runtime.get(key);                 
                   if(null != objLE){
           %>
			   <tr>             
               <td align="center"> <%=key%></td>
               <td align="center"> <%=objLE.getFirstLoadTime() %></td>
               <td align="center"> <%=objLE.getRuntime() %></td>         
            </tr>
            <%}
               }
                %>
         </table><br><br>
         
         
      </form>
   </body>
</html>