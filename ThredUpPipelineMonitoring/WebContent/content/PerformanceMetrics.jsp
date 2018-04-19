<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	
	<h1>Performance Metrics</h1>

	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="buttoncenter">
				<form class="bg-primary" id="home" method="post" name="home"
					action="<%=request.getContextPath()%>/ThredUpController">
					<input type="hidden" name="pageName" value="performance"> <input
						type="hidden" name="action" value=>
					
					<INPUT type=button value="Loading Errors" name="bt"
						onclick="fnSubmit('loading')" class="button" style="height:100px;width:250px">
					
					
					<INPUT type=button value="Copying Errors from S3" name="bt"
						onclick="fnSubmit('copying')" class="button" style="height:100px;width:250px">
					
					<INPUT type=button value="Problematic Queries" name="bt"
						onclick="fnSubmit('queries')" class="button" style="height:100px;width:250px">
					
				</form>
			</div>
		</div>
	</div>
	</div>
	<div class="py-5"></div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<ol class=""></ol>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
	function fnSubmit(navigate) {
		document.home.action.value = navigate;
		document.home.submit();
	}
</SCRIPT>
<STYLE>
.button {
	background-color: #008CBA; /* Blue */
	border: none;
	color: white;
	padding: 10px 12px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	width: 144px;
	height: 58px
}

.buttonlink {
	background: none !important;
	color: inherit;
	border: none;
	padding: 0 !important;
	font: inherit;
	/*border is optional*/
	border-bottom: 1px solid #444;
	cursor: pointer;
}

.buttoncenter {
	text-align: center;
}

html, body {
	height: 100%;
}

html {
	display: table;
	margin: auto;
}

body {
	display: table-cell;
	vertical-align: middle;
}

.buttonlink {
	background: none !important;
	color: inherit;
	border: none;
	padding: 0 !important;
	font: inherit;
	/*border is optional*/
	border-bottom: 1px solid #444;
	cursor: pointer;
}
</STYLE>