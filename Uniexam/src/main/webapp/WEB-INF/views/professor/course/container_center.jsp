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
	});
</script>
<div class="container-center">
	<script type="text/javascript">
		var oldString = "";
		function aler() {
			// 					alert("ciao");
		}
		function storeOld(item) {
// 			alert("ciao");
			// 			oldString = $("#" + item.id).children().html();
			// 			$("#" + item.id).children().html().select();
			oldString = $("#" + item.id).children().html();
			$("#" + item.id).children().select();
			// 			oldString.parent().select();
		}
		function beforeChangeNote(item, idCourse) {
			var newString = $("#" + item.id).children().html();
			if (newString == oldString || newString == "") {
				// 						alert("sono Uguali");
				return;
			}
			// 					alert("sono Diversi");
			changeNote(item, idCourse);
		}
		// 				$(document).ready(function(){
		// 					$("#clearH").click(function(){
		// 						clear(this);
		// 					});
		// 				});
	</script>
	<fieldset>
		<legend><spring:message
								code="message.professor.course.container.center.legendCourses" /></legend>
		<ul>
			<%
				ArrayList<ArrayList<Object>> struct = new ArrayList<ArrayList<Object>>();
				ArrayList<Course> courses = (ArrayList<Course>) request
						.getAttribute("courses");
				int count = 0;
				if (courses != null && courses.size() > 0) {
					for (Course c : courses) {
						struct.add(new ArrayList<Object>());
						struct.get(count).add(c);
						struct.get(count).addAll(c.getGroups());
			%>
			<li class="list_course">
				<article>
					<section id="<%="course" + (count++)%>">
						<a href="#"><h1><%=c.getName()%></h1></a>
					</section>
					<section id="<%="note" + (count)%>" contenteditable="true"
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
		</ul>
	</fieldset>
</div>
<%-- 						<input id="<%="noteInput" + (count)%>" type="text" --%>
<!-- 							style="border: none; background: inherit; dispay: none;" -->
<%-- 							placeholder="<spring:message code='message.professor.course.container.center.nonote' />" /> --%>
