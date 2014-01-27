<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container-up">
		<ul class="dashboard">
			<li><a class="button" href="#" id="courseButton"
				onclick="selectDashBoard(this)"><spring:message
						code="label.gestioneOrdinamenti" /></a></li>
			<li><a class="button" href="#" id="appealButton"
				onclick="selectDashBoard(this)"><spring:message
						code="label.examSession" /></a></li>
			<li><a class="button" href="#" id="groupsButton"
				onclick="selectDashBoard(this)"><spring:message
						code="label.manageCourse" /></a></li>
			<li><a class="button" href="#" id="signButton"
				onclick="selectDashBoard(this)"><spring:message
						code="label.assignCourse" /></a></li>
		</ul>
	</div>
