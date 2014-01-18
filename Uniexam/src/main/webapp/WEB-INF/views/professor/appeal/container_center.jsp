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
%>
<div class="container-center">
	<script type="text/javascript">
		
	</script>
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
	</script>
	<div class="bottonmok" onclick="openPopUpWithAjaxContent('addAppeal','')">Crea Appello</div>
	<fieldset>
		<legend>
			<spring:message
				code="message.professor.course.container.center.legendCourses" />
		</legend>
		<table id="#sorting">
			<%
				if (struct != null) {
							// 				response.sendRedirect("error_page/general_error.jsp?error_type=Null_pointer&error_message=Error_in_appeals");
							// 			}
							for (ArrayList<Object> items : struct) {
			%>
			<tr>
				<td><span class="span_expandible" id="collapseAppeals<%=((Course) items.get(0)).getId()%>">+</span>
				<%=((Course) items.get(0)).getName()%></td>
			</tr>
			<tr style="display: none;" id="Appeals<%=((Course) items.get(0)).getId()%>">
				<td>
					<% if(items.size()>1){%>
					<ol>
						<%for(int i=1;i<items.size();i++){ %>
						<li>
							<%=((Appeal)items.get(i)).getName() %>
						</li>
						<%} %>
					</ol> <%}else{ %> Non ci sono appelli su questo corso <%} %>
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
		</table>
	</fieldset>
</div>
