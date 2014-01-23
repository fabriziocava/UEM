<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.DegreeCourse"%>
<%@page import="java.util.Set" %>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div class="container-center">

	<fieldset>
		<legend><spring:message 	code="message.manager.course.container.center.legendCourses" /></legend>
		<ol id="#sorting">
			<%
				Set<DegreeCourse> courses = (Set<DegreeCourse>) request.getAttribute("courses");
				if (courses != null && courses.size() > 0) {
					for (DegreeCourse c : courses) {
			%>
			<li class="list_course">
				<article>
					<section id="<%="course" + c.getId()%>">
						<a id="<%="acourse" + c.getId()%>" href="#" onclick="getDataFromAjax(this);"><h1><%=c.getName()%></h1></a>
					</section>
					
				</article>
			</li>
			<%
				}
				} else {
			%>
			<spring:message	code="message.professor.course.container.center.nocourse" />
			<%
				}
			%>
		</ol>
	</fieldset>
</div>
