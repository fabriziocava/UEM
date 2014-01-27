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
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("verbalToBeSignedButton"));
		$("#sorting").tablesorter();
	});
	
	function toggle(source) {
		  checkboxes = document.getElementsByName('appealStudent');
		  for(var i=0, n=checkboxes.length;i<n;i++) {
			  checkboxes[i].checked = source.checked;
			  }
	}
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.verbalToBeSigned'/></legend>
		<%
		ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
		if(appealStudent!=null && !appealStudent.isEmpty()) {
			%>
				<form:form action="${pageContext.request.contextPath}/student/verbalToBeSigned/sign">
					<table border="1" style="width: 100%">
						<thead>
							<tr>
								<th><input name="selectAll" type="checkbox" value="selectAll" title="Select all" onclick="toggle(this)" /></th>
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
								<td align="center"><input id="appealStudent_id" name="appealStudent" type="checkbox" value="<%=as.getId()%>" title="<%=as.getAppeal().getCourse().getName()%>" />
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
					<br>
					<table>
						<tr>
							<td><label>Password:</label></td>
							<td><input name="password" type="password" required="required" placeholder="password"></td>
							<td><input type="submit" id="submit" value="<spring:message code='label.sign'/>" style="background-color: green; color: white; font-size: 18px"></td>
						</tr>
					</table>
				</form:form>
			<%
		} else {
			%>
			<label>Non hai esami da firmare</label>
			<%
		}
		%>
		</fieldset>
</div>
