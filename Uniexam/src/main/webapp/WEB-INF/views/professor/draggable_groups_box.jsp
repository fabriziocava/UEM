<!-- Require courses with groups -->
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
%>
<div class="box box-groups draggable" id="box-groups">
		<div class="box-header">
			<span class="span_expandible" id="collapse_drag_collapsable_group">+</span>		
			<spring:message code="message.professor.course.container.center.titleBoxGroups" />
		</div>
		<div class="box-body" id="_drag_collapsable_group">
			<%
				for (Course c : courses) {
			%>
			<ol class="list-courses-box">
				<span class="span_expandible" id="collapselist-groups-box<%=c.getId()%>">+</span>
				<%=c.getName()%>
				<li>
					<ol id="list-groups-box<%=c.getId()%>" class="list-groups-box"
						style="display: none;">
						<%
							for (Group g : c.getGroups()) {
						%>
						<li><a href="#"><%=g.getName()%></a></li>
						<%
							}
						%>
					</ol>
				</li>
			</ol>
			<%
				}
			%>
		</div>
	</div>