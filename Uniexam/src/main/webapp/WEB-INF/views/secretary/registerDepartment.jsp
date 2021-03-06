<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user} - <spring:message code="title.addNewDepartment" /></title>

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
			<c:url var="departmentRegistration" value="saveDepartment.html"/>
			<form:form id="registerDepartmentForm" modelAttribute="department" method="post" action="saveDepartment.html">
			<table>
			<tr>
			<td><form:label path="code"><spring:message code="label.secretary.code" /></form:label></td>
			<td><form:input path="code"/>
				<%--<div style="display: none;"> </div>--%>
				<%--<input type="text" placeholder="code"/>--%>
			</td>
			</tr>
			<tr>
			<td><form:label path="name"><spring:message code="label.secretary.denomination" /></form:label></td>
			<td><form:input path="name"/></td>
			</tr>
			<tr>
			<td><form:label path="Address.street"><spring:message code="label.secretary.street" /></form:label></td>
			<td><form:input path="Address.street"/></td>
			</tr>
			<tr>
			<td><form:label path="Address.city"><spring:message code="label.secretary.city" /></form:label></td>
			<td><form:input path="Address.city"/></td>
			</tr>
			<tr>
			<td><form:label path="Address.zip"><spring:message code="label.secretary.zip" /></form:label></td>
			<td><form:input path="Address.zip"/></td>
			</tr>
			<tr>
			<td><form:label path="Address.state"><spring:message code="label.secretary.state" /></form:label></td>
			<td><form:input path="Address.state"/></td>
			</tr>
			<tr><td></td><td>
			<input type="submit" value="<spring:message code="label.secretary.register" />" />
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