<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("courseButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.courses'/></legend>
		<ol id="#sorting">
		<%
			ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
			if(courses!=null && !courses.isEmpty()) {
				for(Course c : courses) {
		%>
					<li class="list-item" style="border-radius: 4px; ">
						<article>
							<section id="<%="course" + c.getId()%>">
								<span class="span_expandible" id="collapsedivrse<%=c.getId()%>" onclick="getDataFromAjax('course/course_details','<%=c.getId()%>','divrse<%=c.getId()%>');">+</span><%=c.getName()%>
								<div id="divrse<%=c.getId()%>" style="display: none;"></div>
							</section>
						</article>
					<br>
					</li>
		<%			
				}
			} else {
				%>
				<spring:message
					code="message.professor.course.container.center.nocourse" />
				<%				
			}
		%>
		</ol>
	</fieldset>
	
</div>