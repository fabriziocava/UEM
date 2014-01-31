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


<script type="text/javascript">

$(document).ready(function () {
	selectingFromDashBoard(document.getElementById("assignButton"));
});
</script>

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
				<td><span class="span_expandible" onclick="collapseMok(this)"
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
			
			
					<ol id="#sorting"   >
				
				<li class="list-item" style="border-radius: 4px; ">
						<article>
							<section id="<%="course" + C.getId()%>">
								<span class="span_expandible" id="collapsedivrse<%=C.getId()%>" onclick="collapseMok(this); getDataFromAjax('assignCourse/course_details','<%=C.getId()%>','divrse<%=C.getId()%>');">+</span><%=C.getName()%>
								<div id="divrse<%=C.getId()%>" style="display: none;"></div>
							</section>
						</article>
					</li>
				
				
				</ol>
			<% } %>
		<%} }  else { %>
					
				Non ci sono corsi
				
				<%} %>

		</td>
		<%
					}
					} 
				%>
		
		
		</table>
		
		</fieldset>
					


	
</div>