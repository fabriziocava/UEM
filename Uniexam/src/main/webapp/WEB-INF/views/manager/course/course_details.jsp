<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
	<%
		Course c=(Course)request.getAttribute("course");
	%>

<div id="course_details_<%=c.getId()%>" style="display: none;">
	<script type="text/javascript">
	$(document).ready(function(){
		//go this in fade in
		$("#course_details_<%=c.getId()%>").slideDown(1000);
	});
	</script>
	<table class="tablemok">
	<caption><strong><spring:message code="message.professor.course.course_details.caption" /></strong></caption>
	<tbody>
		<tr>
			<th>
			<spring:message code="message.professor.course.attribute.code" />
			</th>
			<td><%=c.getCode() %></td>
		</tr>
			<tr>
			<th>
			<spring:message code="message.professor.course.attribute.url" />
			</th>
			<td><%=c.getWebSite() %></td>
		</tr>
		<tr>
			<th>
			<spring:message code="message.professor.course.attribute.credits" />
			</th>
			<td><%=c.getCredits() %></td>
		</tr>
		
	
	<tr>
	<th>
	<spring:message code="message.manager.course.holder" />
	</th>
	<%if(c.getHolder()!=null){ %>
	<td><%= c.getHolder().getName() + " " + c.getHolder().getSurname() %></td>
	<%} else{ %>
	 <td><%= "Corso non assegnato" %> </td> 
	 <%} %>
	</tr>

	
	<tr>
	<th>
	<spring:message code="message.manager.course.holder" />
	</th>
	<td><%if(c.getHolder()!=null ){%>
	<a  href="#" class="bottonmok"><spring:message code="message.general.manage" /> </a> 
	<%}else{ %>
	<a href="#" class="bottonmok"><spring:message code="message.general.add" /></a>
	<%} %>
	</td>
	</tr>
	
	<tr>
	<th>
	<spring:message code="message.professor.course.attribute.requested_courses" />
	</th>
	<td><%if(c.getRequestedCourses()!=null && c.getRequestedCourses().size()>0){%>
	<a href="#" class="bottonmok" onclick="openPopUpWithAjaxContent('requested_course',<%=c.getId()%>)"><spring:message code="message.general.manage" /></a>
	<%}else{ %>
	<a href="#" class="bottonmok" onclick="openPopUpWithAjaxContent('requested_course',<%=c.getId()%>)"><spring:message code="message.general.add" /></a>
	<%} %>
	</td>
	</tr>
	
	<tr>
	</tbody>
	</table>
</div>
