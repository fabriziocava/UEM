<!-- Require courses with groups -->
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
%>
<div class="box-groups">
		<div class="box-header">
			<spring:message
				code="message.professor.course.container.center.titleBoxGroups" />
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				$("span[id^='collapse'],span[id^='expanse']").each(function() {
					$(this).bind("click", function() {
						var idOld = this.id;
						var realID;
						var newID;
						if(idOld.match("collapse")){
							realID = idOld.replace("collapse", "");
							newID="expanse";
							$(this).html("-");
						}else{
							realID = idOld.replace("expanse", "");
							newID="collapse";
							$(this).html("+");
						}
						var idd = "list-groups-box" + realID;
						$("#" + idd).toggle();
						$(this).attr("id", newID+realID);
					});
				});
				$(".box-groups").draggable();
			});
		</script>
		<div class="box-body">
			<%
				for (Course c : courses) {
			%>
			<ol class="list-courses-box">
				<span style="cursor:pointer;" id="collapse<%=c.getId()%>">+</span>
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