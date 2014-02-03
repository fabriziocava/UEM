<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container-up">
		<ul class="dashboard">
			<li><a class="button" href="#" id="departmentButton" title="<spring:message code='label.department'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.department" /></a></li>
			<li><a class="button" href="#" id="professorButton" title="<spring:message code='label.professor'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.professor" /></a></li>
			<li><a class="button" href="#" id="studentButton" title="<spring:message code='label.student'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.student" /></a></li>
		</ul>
	</div>
