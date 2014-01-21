<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
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
	$(document).ready(function(){
		dialogViewListStudent();
// 		initCollapsable();
	});
</script>

<div id="dialog_content">


<fieldset>
		<legend>
			<spring:message
				code="message.professor.course.container.center.legendCourses" />
		</legend>
		<%
			ArrayList<AppealStudent> appealStudents = (ArrayList<AppealStudent>) request
					.getAttribute("appealstudents");
			if (appealStudents != null && appealStudents.size() > 0) {
		%>
		<div><%=appealStudents.get(0).getAppeal().getName()%>
			|
			<%=appealStudents.get(0).getAppeal().getLocation()%>
			<br />
			<%=appealStudents.get(0).getAppeal().getDescription()%></div>
		<table class="tablemok">
			<tr style="text-align: center;">
				<th style="padding: 0px 20px 0px 20px;">Matricola</th>
				<th style="padding: 0px 20px 0px 20px;">Nome</th>
				<th style="padding: 0px 20px 0px 20px;">Voto</th>
				<th style="padding: 0px 20px 0px 20px;">Nota</th>
			</tr>
			<%
				for (AppealStudent app : appealStudents) {
			%>
			<tr style="text-align: center;">
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getSerialNumber()%></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getName()%> <%=app.getStudent().getSurname()%></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getTemporany_vote()%></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getNote()%></td>
			</tr>
			<%} %>
		</table>
		<%}else{%>
		No students
		<%} %>

	</fieldset>
















</div>