<<<<<<< HEAD
<%@page import="it.unical.uniexam.MokException"%>
<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="java.util.Set"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("verbalToBeSignedButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.verbalToBeSigned'/></legend>
		<%
		ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
		if(appealStudent!=null && !appealStudent.isEmpty()) {
			%>
				<table border="1" style="width: 100%">
					<thead>
						<tr>
							<th></th>
							<th><spring:message	code="label.code" /></th>
							<th><spring:message	code="label.course" /></th>
							<th><spring:message	code="message.professor.course.attribute.credits" /></th>
							<th><spring:message	code="label.vote" /></th>		
							<th><spring:message	code="label.date" /></th>
						</tr>
					</thead>			
			<%
				for(AppealStudent as : appealStudent) {
					try {
					%>
						<tr>
							<td align="center"><input name="html" type="checkbox" value="html" /></td>
							<td align="center"><%=as.getAppeal().getCourse().getCode()%></td>
							<td><%=as.getAppeal().getCourse().getName()%></td>
							<td align="center"><%=as.getAppeal().getCourse().getCredits()%></td>
							<td align="center"><%=as.getTemporany_vote()>30.0 ? "30 L" : as.getTemporany_vote().intValue()%></td>
							<td align="center"><%=as.getAppeal().getExamDate()%></td>
						</tr>
					<%
					} catch (Exception e) {
						new MokException(e);
					}
				}
			%>
				</table>
			<%
		} else {
			%>
			<label>Non esami da firmare</label>
			<%
		}
		%>
=======
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="java.util.Set"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("verbalToBeSignedButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.verbalToBeSigned'/></legend>
		<ol id="#sorting">
			
		</ol>
>>>>>>> branch 'Fabrizio3' of https://github.com/mok89/UEM.git
	</fieldset>
	
</div>
