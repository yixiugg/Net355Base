<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#testBtn").click(function() {
			$.getJSON("mvc/hello/getAdmin/1", function(data) {
				$(data).each(function() {
					$("#result").html("welcome "+this.username);
				});
			});
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<button id="testBtn" type="button">Click me!</button>
</div>
<div id="result"></div>
</body>
</html>