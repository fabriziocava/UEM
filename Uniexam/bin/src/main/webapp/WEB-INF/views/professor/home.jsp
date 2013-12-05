<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%if(request.getAttribute("login")!=null){
	response.sendRedirect("professor/home?user="+request.getAttribute("user"));
}
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user}</title>

<link href="/uniexam/res/css/professor.css" media="all" rel="stylesheet" type="text/css" />
<script src="/uniexam/res/jquery-2.0.3.js">
	
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
<%-- 			alert("path: "+"<%=request.getPathInfo()%>"); --%>
		});
	</script>
	<header>
		<div class="header-logo">
			<a href="/home"> <img alt="home" src="/uniexam/res/img/logo.png"
				width="30" height="30">
			</a>
		</div>
	</header>
	<div class="container">
		<div class="container-up"></div>
		<div class="container-left"></div>
		<div class="container-center"></div>
		<div class="container-right"></div>
		<div class="container-down"></div>
	</div>
	<footer></footer>
</body>
</html>