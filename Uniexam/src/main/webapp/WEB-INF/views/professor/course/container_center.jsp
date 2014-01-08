<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("courseButton"));
	});
</script>
<div class="container-center" style="width: 66%">
	<ul>
		<%
			ArrayList<Course> courses = (ArrayList<Course>) request
					.getAttribute("courses");
			int count = 0;
			if (courses != null && courses.size() > 0) {
				for (Course c : courses) {
		%>
		<li class="list_course">
			<div>
				<div id="<%="course" + (count++)%>">
					<a href="#"><h1><%=c.getName()%></h1></a>
				</div>
				<div style="float: right;">
					<div class="box-header"><spring:message code="message.professor.course.container.center"/></div>
					<div class="box-body">
						<%for(Group g:c.getGroups()){ %>
						<a href="#"><%=g.getName() %></a>
						<%} %>
					</div>
				</div>
				<div id="<%="note" + (count)%>">
					<h1><%=c.getNote()%></h1>
				</div>
			</div>
		</li>
		<%
			}
			} else {
		%>
		No Courses!
		<%
			}
		%>
	</ul>
</div>