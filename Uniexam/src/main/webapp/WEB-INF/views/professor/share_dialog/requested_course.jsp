<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#dialog").dialog({
			autoOpen : true,
			width : "75%",
			show : {
				effect : "blind",
				duration : 500
			},
			hide : {
				effect : "explode",
				duration : 500
			}
		});
	});
</script>
<%
	Course c = (Course) request.getAttribute("course");
%>
<div id="dialog_content">
	THIS IS DIALOG CONTENT
	<h1 align="center"><%=c.getName()%></h1>
	
	<div>
		<table>
			<%
				for(RequestedCourse req:c.getRequestedCourses()){
			%>
		<tr><td>
		<span class="span_expandible" id="collapserequestCourse<%=req.getCourse().getId()%>">+</span> <%=req.getCourse().getName()%>
		</td></tr>
		<tr id="requestCourse<%=req.getCourse().getId()%>" style="display: none;"><td>
		<div>
		<%req.getPolicyOfRequest(); %>///******
		</div>
		</td></tr>
			<%}
				
			%>
			<%
				
			%>
		</table>
	</div>


</div>













