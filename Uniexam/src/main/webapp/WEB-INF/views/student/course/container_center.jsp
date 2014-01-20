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
	<div align="center">
		<label style="text-align: center; color: maroon; text-transform: uppercase;"><b><spring:message code='label.courses'/></b></label>	
		<br><br>
	</div>
	<%
		ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
		if(courses!=null && !courses.isEmpty()) {
			for(Course c : courses) {
	%>
				<a id="<%="acourse" + c.getId()%>" href="#" onclick=""><b><%=c.getName()%></b></a>
				<br>
	<%			
			}
		}
	%>
	
</div>