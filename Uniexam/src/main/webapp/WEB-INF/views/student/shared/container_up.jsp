<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container-up">
		<ul class="dashboard">
			<li><a class="button" href="#" id="courseButton" title="<spring:message code='label.courses'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.courses" /></a></li>
			<li><a class="button" href="#" id="carrierButton" title="<spring:message code='label.carrier'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.carrier" /></a></li>
			<li><a class="button" href="#" id="verbalSignButton" title="<spring:message code='label.verbalSign'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.verbalSign" /></a></li>
			<li><a class="button" href="#" id="groupsButton" title="<spring:message code='label.groups'/>"
				onclick="selectDashBoard(this)"><spring:message
						code="label.groups" /></a></li>
		</ul>
	</div>
