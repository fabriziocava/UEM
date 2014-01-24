<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
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
		selectingFromDashBoard(document.getElementById("appealButton"));
	});
</script>
<%
	ArrayList<ArrayList<Object>>struct=(ArrayList<ArrayList<Object>>)request.getAttribute("struct");
	ArrayList<Appeal>other=(ArrayList<Appeal>)request.getAttribute("noCourse");
%>
<div class="container-center">
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
		
	</script>
	<div class="bottonmok"
		onclick="openPopUpWithAjaxContent('addAppeal','')">Crea Appello</div>
	<fieldset>
		<legend>
			<spring:message
				code="message.professor.course.container.center.legendCourses" />
		</legend>
		<table id="#sorting" class="tablemok">
			<%
				if (struct != null) {
					// 				response.sendRedirect("error_page/general_error.jsp?error_type=Null_pointer&error_message=Error_in_appeals");
					// 			}
					for (ArrayList<Object> items : struct) {
			%>
			<tr>
				<td><span class="span_expandible"
					id="collapseAppeals<%=((Course) items.get(0)).getId()%>" onclick="collapseMok(this)">+</span> <%=((Course) items.get(0)).getName()%></td>
			</tr>
			<tr style="display: none;"
				id="Appeals<%=((Course) items.get(0)).getId()%>">
				<td>
					<%
						if (items.size() > 1) {
					%>
					<table class="tablemok">
						<thead>
							<tr>
								<th><spring:message
										code='message.professor.appeal.add_appeal.name' /></th>
								<th><spring:message
										code='message.professor.appeal.add_appeal.numberOfInscribed' /></th>
								<th><spring:message
										code='message.professor.appeal.add_appeal.maxNumberOfInscribed' /></th>
								<th><spring:message
										code='message.professor.appeal.add_appeal.examDate' /></th>
								<th><spring:message
										code='message.professor.appeal.add_appeal.description' /></th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 1; i < items.size(); i++) {
												Appeal appeal = ((Appeal) items.get(i));
							%>
							<tr class="list-item" style="text-align: center;">
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.name.description'/>"
									class="bottonmok"
									onclick="openPopUpWithAjaxContent('viewAppeal','<%=appeal.getId()%>')"><%=appeal.getName()%></td>
								<td class="bottonmok"
									onclick="openPopUpWithAjaxContent('viewListStudent','<%=appeal.getId()%>')"
									title="<spring:message code='message.professor.appeal.add_appeal.numberOfInscribed.description'/>"
									style="display: table-cell;">
									<%=appeal.getAppeal_student().size()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.maxNumberOfInscribed.description'/>"><%=appeal.getMaxNumberOfInscribed()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.examDate.description'/>"><%=appeal.getExamDate()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.description.description'/>"><%=appeal.getDescription()%></td>
							</tr>
							<tr>
								<td><div class="line-top"></div></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table> <%
 	} else {
 %> <spring:message code="message.professor.course.container.center.nocourse" /> <%
 	}
 %>
				</td>
			<tr>
				<%
					}
					} else {
				%>
				<spring:message code="message.professor.course.container.center.nocourse" />
				<%
					}
				%>
			
			<tr>
				<td><div class="line-top"></div></td>
			</tr>
			<tr>
				<td><span class="span_expandible" onclick="collapseMok(this)" id="collapseAppealsNoCourse">+</span>Altri
					appelli</td>
			</tr>
			<tr style="display: none;" id="AppealsNoCourse">
				<td><div>
						<%
							if (other != null && other.size() > 0) {
						%>
						<table class="tablemok">
							<thead>
								<tr>
									<th><spring:message
											code='message.professor.appeal.add_appeal.name' /></th>
									<th><spring:message
											code='message.professor.appeal.add_appeal.numberOfInscribed' /></th>
									<th><spring:message
											code='message.professor.appeal.add_appeal.maxNumberOfInscribed' /></th>
									<th><spring:message
											code='message.professor.appeal.add_appeal.examDate' /></th>
									<th><spring:message
											code='message.professor.appeal.add_appeal.description' /></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Appeal appeal : other) {
								%>
								<tr class="list-item" style="text-align: center;">
									<td
									title="<spring:message code='message.professor.appeal.add_appeal.name.description'/>"
									class="bottonmok"
									onclick="openPopUpWithAjaxContent('viewAppeal','<%=appeal.getId()%>')"><%=appeal.getName()%></td>
								<td class="bottonmok"
									onclick="openPopUpWithAjaxContent('viewListStudent','<%=appeal.getId()%>')"
									title="<spring:message code='message.professor.appeal.add_appeal.numberOfInscribed.description'/>"
									style="display: table-cell;">
									<%=appeal.getAppeal_student().size()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.maxNumberOfInscribed.description'/>"><%=appeal.getMaxNumberOfInscribed()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.examDate.description'/>"><%=appeal.getExamDate()%></td>
								<td
									title="<spring:message code='message.professor.appeal.add_appeal.description.description'/>"><%=appeal.getDescription()%></td>
								</tr>
								<tr>
									<td><div class="line-top"></div></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							} else {
						%>
				<spring:message code="message.professor.course.container.center.nocourse" />
				<%
					}
				%>
					</div></td>
			</tr>
		</table>
	</fieldset>
</div>
