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
					id="collapseAppeals<%=((Course) items.get(0)).getId()%>">+</span> <%=((Course) items.get(0)).getName()%></td>
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
								<th>Name</th>
								<th>Inscribed</th>
								<th>Date exam</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 1; i < items.size(); i++) {
												Appeal appeal = ((Appeal) items.get(i));
							%>
							<tr class="list-item" style="text-align: center;">
								<td title="Name appeal" class="bottonmok" onclick="openPopUpWithAjaxContent('viewAppeal','<%=appeal.getId()%>')"><%=appeal.getName()%></td>
								<td title="Inscribed"><%=appeal.getStudentsInscribed().size()%></td>
								<td title="date of exam"><%=appeal.getExamDate()%></td>
								<td title="description"><%=appeal.getDescription()%></td>
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
					 %> Non ci sono appelli su questo corso <%
					 	}
					 %>
				</td>
			<tr>
				<%
					}
					} else {
				%>
				<spring:message
					code="message.professor.course.container.center.nocourse" />
				<%
					}
				%>
			
			<tr>
				<td><div class="line-top"></div></td>
			</tr>
			<tr>
				<td><span class="span_expandible" id="collapseAppealsNoCourse">+</span>Altri
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
									<th>Name</th>
									<th>Inscribed</th>
									<th>Date exam</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Appeal appeal : other) {
								%>
								<tr class="list-item" style="text-align: center;">
									<td title="Name appeal" class="bottonmok" onclick="openPopUpWithAjaxContent('viewAppeal','<%=appeal.getId()%>')"><%=appeal.getName()%></td>
									<td title="Inscribed"><%=appeal.getStudentsInscribed().size()%></td>
									<td title="date of exam"><%=appeal.getExamDate()%></td>
									<td title="description"><%=appeal.getDescription()%></td>
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
					</div></td>
				<spring:message
					code="message.professor.course.container.center.nocourse" />
				<%
					}
				%>
			</tr>
		</table>
	</fieldset>
</div>
