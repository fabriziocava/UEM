<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user} - Add new Manager</title>

<link href="${pageContext.request.contextPath}/res/css/secretary.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="res/jquery-2.0.3.js"> </script>
</head>

<body>
	<header>
	
	</header>
	<div class="container">
		<div class="container-up"></div>
		<div class="container-left"></div>
		<div class="container-center">
			<form:form id="registerManagerForm" modelAttribute="registerManager" method="post" action="">
				<table>
					<tr>
						<td><form:label path="username">Username</form:label></td>
						<td><form:input path="username"/></td>
					</tr>
					<tr>
						<td><form:label path="firstName">First Name</form:label></td>
						<td><form:input path="firstName"/></td>
					</tr>
					<tr>
						<td><form:label path="lastName">Last Name</form:label></td>
						<td><form:input path="lastName"/></td>
					</tr>
				<tr><td></td><td>
				<input type="submit" value="Register" />
				</td></tr>
				</table>
				</form:form>
			
		</div>
		<div class="container-right"></div>
		<div class="container-down"></div>
	</div>
	<footer></footer>
</body>
</html>