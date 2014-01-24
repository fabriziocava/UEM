<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.DegreeCourse"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.Set" %>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div class="container-center">

	<fieldset>
		<legend><spring:message 	code="message.manager.course.container.center.legendCourses" /></legend>
		<table id="#sorting" class="tablemok">
			<%
				ArrayList<Course> Courses=(ArrayList<Course>) request.getAttribute("c");
				Set<DegreeCourse> courses = (Set<DegreeCourse>) request.getAttribute("courses");
				if (courses != null && courses.size() > 0) {
					for (DegreeCourse c : courses) {
			%>
			
			<tr>
				<td><span class="span_expandible"
					id="collapseSessions<%= (c.getId()) %>">+</span> <%= c.getName()%></td>
			</tr>
			
			<tr style="display: none;"
			id="Sessions<%=( c.getId()) %>">
		
			<td>	
			<%
				if (Courses !=null && Courses.size()!=0) {
					
					for(Course C: Courses){
						
						if(C.getDegreeCourse().getId()!=null && c.getId() != null && C.getDegreeCourse().getId()==c.getId()){
					
					%>
			
				<table class="tablemok">
						<thead>
							<tr>
								<th><spring:message
										code='message.manager.course.name' /></th>
								<th><spring:message
										code='message.manager.course.code' /></th>
								<th><spring:message
										code='message.manager.course.credit' /></th>
								<th><spring:message
										code='message.manager.course.holder' /></th>
							</tr>
						</thead>
						<tbody>
						
						<tr class="list-item" style="text-align: center;">
								<td
									title="<spring:message code='message.manager.course.name.description'/>"
									class="bottonmok"
									onclick=""><%=C.getName()%></td>
								<td
									title="<spring:message code='message.manager.course.code.description'/>"><%=C.getCode()%></td>
								<td
									title="<spring:message code='message.manager.course.credit.description'/>"><%=C.getCredits()%></td>
								<td
									title="<spring:message code='message.manager.course.holder.description'/>"></td>
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