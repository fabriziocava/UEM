
<%@page import="it.unical.uniexam.hibernate.domain.ExamSession "%>
<%@page import="it.unical.uniexam.hibernate.domain.DegreeCourse "%>
<%@page import="java.util.Set"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="${pageContext.request.contextPath}/res/css/menuTree.css"
	media="all" rel="stylesheet" type="text/css" />



<script type="text/javascript">
	$(document).ready(function () {
		selectingFromDashBoard(document.getElementById("examButton"));
		$("#sorting").tablesorter();
		$("#nav li:has(ul)").click(function(event) {
			if (this == event.target) {
				$(this).toggleClass('clicked').children('ul').slideToggle();
				$(this).find('li:has(ul)').removeClass('clicked').find("ul").slideUp();
				$(this).siblings().removeClass('clicked').find("ul").slideUp();
				alineamentoContainer();
				
			}
		}).addClass('has_ul');
	});
</script>

<%
	Set<ExamSession> examsession=(Set<ExamSession>)request.getAttribute("examsession");
	Set<DegreeCourse> degreecourse=(Set<DegreeCourse>)request.getAttribute("courses");
%>
<div class="container-center">

		<div class="bottonmok" onclick="openPopUpWithAjaxContent('addSession','')">Crea Sessione d'Esame</div>
		<fieldset>
		<legend>
			<spring:message
				code="message.manager.examsession.container.center.legendExamSession" />
		</legend>
		
		
		
		<table id="#sorting" class="tablemok">
		
		<% 
			if(degreecourse.size()!=0){
				
				for(DegreeCourse dg: degreecourse){
					
		%>
		<tr>
				<td><span class="span_expandible" onclick="collapseMok(this)"
					id="collapseSessions<%= (dg.getId()) %>">+</span> <%= dg.getName()%></td>
		</tr>
		<tr style="display: none;"
		id="Sessions<%=( dg.getId()) %>">
		
		<td>		
		<%
				if (examsession.size()!=0) {
					
					for(ExamSession es: examsession){
						
						if(es.getDegreecourse().getId()==dg.getId()){
					
					%>
		
					<table class="tablemok">
						<thead>
							<tr>
								<th><spring:message
										code='message.manager.examsession.description' /></th>
								<th><spring:message
										code='message.manager.examsession.startdate' /></th>
								<th><spring:message
										code='message.manager.examsession.enddate' /></th>
								<th><spring:message
										code='message.manager.examsession.degreecourse' /></th>
							</tr>
						</thead>
						<tbody>
							
							<tr class="list-item" style="text-align: center;">
								<td
									title="<spring:message code='message.manager.examsession.description.description'/>"
									class="bottonmok"
									onclick="openPopUpWithAjaxContent('view_examsession','<%=es.getId()%>')"><%=es.getDescription()%></td>
								<td
									title="<spring:message code='message.manager.examsession.startdate.description'/>"><%=es.getDataInizio()%></td>
								<td
									title="<spring:message code='message.manager.examsession.enddate.description'/>"><%=es.getDataFine()%></td>
								<td
									title="<spring:message code='message.manager.examsession.degreecourse.description'/>"><%=es.getNameDegreeCoures()%></td>
							</tr>
							<tr>
								<td><div class="line-top"></div></td>
							</tr>
						
						</tbody>
						
					</table>
					<% } %>
				
				<%} }  else { %>
					
				Non ci sono sessioni
				
				<%} %>

		</td>
		<%
					}
					} 
				%>
		
		
		</table>
		
		</fieldset>
					


	
</div>
