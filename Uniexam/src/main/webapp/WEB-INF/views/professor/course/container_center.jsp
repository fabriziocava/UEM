<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("courseButton"));
	});
</script>
<div class="container-center" style="width: 66%">
	<ul>
		<%
		ArrayList<ArrayList<Object>>struct=new ArrayList<ArrayList<Object>>();
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
			<div>
				<div id="<%="course" + (count++)%>">
					<a href="#"><h1><%=c.getName()%></h1></a>
				</div>
				<div id="<%="note" + (count)%>">
					<h1><%=c.getNote()%></h1>
				</div>
			</div>
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
	<div style="float: right;">
		<div class="box-header">
			<spring:message
				code="message.professor.course.container.center.titleBoxGroups" />
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("span[id^='collapse']" ).each(function() {
				  $(this).bind( "click", function() {
					  var RE=new RegExp("\b(collapse|expase)(.+)\b","g");
					  var dg=this.id;
					  alert("1"+dg+"2");
					  var red=dg.replace(RE,"$2");
					  var idd="list-groups-box"+red;
					  alert(""+red);
					  $("#"+idd).toggle();
				  });
			});
		});
		</script>
		<div class="box-body">
			<%for(Course c:courses){ %>
			<ol class="list-courses-box">
			<span id="collapse<%=c.getId()%>">+</span> <%=c.getName() %>
				<li>
					<ol id="list-groups-box<%=c.getId()%>" class="list-groups-box" style="display: none;">
						<%for (Group g : c.getGroups()) {%>
						<li><a href="#"><%=g.getName()%></a></li>
						<%}%>
					</ol>
				</li>
			</ol>
			<%} %>
		</div>
	</div>
</div>