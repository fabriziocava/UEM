<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container-up">
		<ul class="dashboard">
			<li><a class="button" href="${pageContext.request.contextPath}/manager/ordinamento" id="ordinamentiButton" title="<spring:message code='label.gestioneOrdinamenti.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.gestioneOrdinamenti" /></a></li>
			<li><a class="button" href="${pageContext.request.contextPath}/manager/exam_session" id="examButton" title="<spring:message code='label.examSession.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.examSession" /></a></li>
			<li><a class="button" href="#" id="courseButton" title="<spring:message code='label.course.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.manageCourse" /></a></li>
			<li><a class="button" href="${pageContext.request.contextPath}/manager/assignCourse" id="assignButton" title="<spring:message code='label.assignCourse.description'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.assignCourse" /></a></li>
		</ul>
	</div>
