<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container-up">
		<ul class="dashboard">
			<li><a class="button" href="#" id="courseButton" title="<spring:message code='label.course.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.course" /></a></li>
			<li><a class="button" href="#" id="appealButton" title="<spring:message code='label.appeal.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.appeal" /></a></li>
			<li><a class="button" href="#" id="groupsButton" title="<spring:message code='label.groups.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.groups" /></a></li>
			<li><a class="button" href="#" id="signButton" title="<spring:message code='label.sign.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.sign" /></a></li>
		</ul>
	</div>
