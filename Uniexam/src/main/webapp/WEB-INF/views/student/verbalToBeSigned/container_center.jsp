<%@page import="it.unical.uniexam.DateFormat"%>
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
	
	$(document).ready(function() {
	    $('#selectall').click(function(event) {  //on click 
	        if(this.checked) { // check select status
	            $('.checkbox').each(function() { //loop through each checkbox
	                this.checked = true;  //select all checkboxes with class "checkbox"               
	            });
	        }else{
	            $('.checkbox').each(function() { //loop through each checkbox
	                this.checked = false; //deselect all checkboxes with class "checkbox"                       
	            });         
	        }
	    });
	    
	});
	
	function verify() {
		var isCorrect = false;
		$('.checkbox').each(function() {
			if(this.checked)
				isCorrect = true;
		});
		if(!isCorrect)
			alert("Selezionare almeno un esame!");
		return isCorrect;
	}
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.verbalToBeSigned'/></legend>
		<%
		ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
		if(appealStudent!=null && !appealStudent.isEmpty()) {
			%>
				<form:form action="${pageContext.request.contextPath}/student/verbalToBeSigned/sign" id="verbalSign" modelAttribute="idAppealStudent" method="post" onsubmit="return verify()">
					<table id="table" border="1" style="width: 100%">
						<thead>
							<tr>
								<th><input type="checkbox" id="selectall" /></th>
								<th><spring:message	code="label.code" /></th>
								<th><spring:message	code="label.course" /></th>
								<th><spring:message	code="message.professor.course.attribute.credits" /></th>
								<th><spring:message	code="label.vote" /></th>		
								<th><spring:message	code="label.date" /></th>
							</tr>
						</thead>			
				<%
					Course course = null;
					for(AppealStudent as : appealStudent) {
						try {
							if(as.getAppeal()!=null)
								course = as.getAppeal().getCourse();
							else if(as.getCourse()!=null)
								course = as.getCourse();
							if(course!=null) {
							%>
								<tr>
									<td align="center" rowspan="2"><input class="checkbox" name="appealStudent<%=as.getId()%>" type="checkbox" value="<%=as.getId()%>" title="<%=as.getAppeal().getCourse().getName()%>" />
									<td align="center"><%=course.getCode()%></td>
									<td><%=course.getName()%></td>
									<td align="center"><%=course.getCredits()%></td>
									<td align="center"><%=as.getTemporany_vote()>30.0 ? "30 L" : as.getTemporany_vote().intValue()%></td>
								<!-- INSERIRE DATA IN APPEAL-STUDENT -->
									<td align="center"><%=DateFormat.getDayMonthYear(as.getAppeal().getExamDate())%></td>
								</tr>
								<tr>
									<td align="left" colspan="5"><%=as.getNote()%></td>
								</tr>
							<%
							}
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
							<td><input name="password" type="password" id="password" required="required" placeholder="password"></td>
							<td><input type="submit" id="submit" value="<spring:message code='label.sign'/>" style="background-color: green; color: white; font-size: 18px"></td>
							<%
							Boolean error = (Boolean) request.getAttribute("error");
							if(error!=null && error) {
								%>
								<td><label style="color: red;">Password errata! Riprovare.</label></td>
								<%
							}
							%>
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
