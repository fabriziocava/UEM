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
		selectingFromDashBoard(document.getElementById("courseButton"));
		$("#sorting").tablesorter();
	});
</script>
<div class="container-center">
	<script type="text/javascript">
		var oldString = "";
		function storeOld(item) {
			oldString = $("#" + item.id).children().html();
			$("#" + item.id).children().select();
		}
		function beforeChangeNote(item, idCourse) {
			var newString = $("#" + item.id).children().html();
			if (newString == oldString || newString == "") {
				return;
			}
			changeNote(item, idCourse);
		}
	</script>
	<fieldset>
		<legend><spring:message
								code="message.professor.course.container.center.legendCourses" /></legend>
		<ol id="#sorting">
			<%
				ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
						if (courses != null && courses.size() > 0) {
							for (Course c : courses) {
			%>
			<li class="list-item" style="border-radius: 4px; ">
				<article>
					<section id="<%="course" + c.getId()%>">
					<span class="span_expandible" id="collapsedivrse<%=c.getId()%>" onclick="collapseMok(this); getDataFromAjax('course/course_details','<%=c.getId()%>','divrse<%=c.getId()%>');">+</span><%=c.getName()%>
<%-- 						<a id="<%="acourse" + c.getId()%>" href="#" onclick="getDataFromAjax('course/course_details','<%=c.getId()%>','divrse<%=c.getId()%>');"><h1><%=c.getName()%></h1></a> --%>
						<div id="divrse<%=c.getId()%>" style="display: none;"></div>
					</section>
					<section id="<%="note" + c.getId()%>" contenteditable="true" spellcheck="true"
						onfocus="storeOld(this)"
						onblur="beforeChangeNote(this,'<%=c.getId()%>')">
						<%
							if (c.getNote() == null || c.getNote().length() < 1
											|| c.getNote().equals("undefined")) {
						%>
						<h1>
							<spring:message
								code="message.professor.course.container.center.nonote" />
						</h1>
						<%
							} else {
						%>
						<h1><%=c.getNote()%></h1>
						<%
							}
						%>
					</section>
				</article>
<%-- 				<a href="${pageContext.request.contextPath}/professor/ajax/course/course_details">LINK TO TRY</a> --%>
			</li>
			<%
				}
				} else {
			%>
			<spring:message
				code="message.professor.course.container.center.nocourse" />
			<%
				}
			%>
		</ol>
	</fieldset>
</div>
<%-- 						<input id="<%="noteInput" + (count)%>" type="text" --%>
<!-- 							style="border: none; background: inherit; dispay: none;" -->
<%-- 							placeholder="<spring:message code='message.professor.course.container.center.nonote' />" /> --%>
