<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:url var="userRegistration" value=""/>
			<form:form id="registerForm" modelAttribute="manager" method="post" action="">
			<table>
			<tr>
			<td><form:label path="name">Name</form:label></td>
			<td><form:input  path="name"/></td>
			</tr>
			<tr>
			<td><form:label path="surname">Surname</form:label></td>
			<td><form:input  path="surname"/></td>
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