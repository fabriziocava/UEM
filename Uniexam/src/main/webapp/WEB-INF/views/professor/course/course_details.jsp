<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
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
<div id="course_details_<%=c.getId()%>" class="startHide">
	<script type="text/javascript">
	$(document).ready(function(){
		//go this in fade in
		$("#course_details_<%=c.getId()%>").slideDown(1000);
	});
	function openDialogCommissary(idCourse){
		names=new Array();
		names.push("id");
		values=new Array();
		values.push(idCourse);
		var title="Exam's Commissionary";
		var path="/ajax/dialog/course/list_commissionary";
		var type="POST";
		var arrayObj=openDialogWithAjaxContent(path, type, names, values, title);
		arrayObj[0].done(function(){
			var dial=arrayObj[1];
			var buttons={};
			buttons.Close = function() { 
					dial.dialog("close");
			}
			dial.dialog("option", "buttons", buttons);
		});
	}
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
	</tr><tr>
	<th>
	<spring:message code="message.professor.course.attribute.requested_courses" />
	</th>
	<td><%if(c.getRequestedCourses()!=null && c.getRequestedCourses().size()>0){%>
	<buttonmok onclick="openPopUpWithAjaxContent('requested_course',<%=c.getId()%>)"><spring:message code="message.general.manage" /></buttonmok>
	<%}else{ %>
	<buttonmok onclick="openPopUpWithAjaxContent('requested_course',<%=c.getId()%>)"><spring:message code="message.general.add" /></buttonmok>
	<%} %>
	</td>
	</tr><tr>
	<th>
	<spring:message code="message.professor.course.attribute.commissions" />
	</th>
	<td><%if(c.getCommissionProfessors()!=null && c.getCommissionProfessors().size()>0){%>
	<buttonmok onclick="openDialogCommissary('<%=c.getId()%>')"><spring:message code="message.general.manage" /></buttonmok>
	<%}else{ %>
	<buttonmok><spring:message code="message.general.add" /></buttonmok>
	<%} %>
	</td>
	</tr><tr>
	<th>
	<spring:message code="message.professor.course.attribute.groups" />
	</th>
	<td><%if(c.getGroups()!=null && c.getGroups().size()>0){%>
	<buttonmok><spring:message code="message.general.manage" /></buttonmok>
	<%}else{ %>
	<buttonmok><spring:message code="message.general.add" /></buttonmok>
	<%} %>
	</td>
	</tr>
	<% %>
	</tbody>
	</table>
	<div id="dialog_space"></div>
</div>
