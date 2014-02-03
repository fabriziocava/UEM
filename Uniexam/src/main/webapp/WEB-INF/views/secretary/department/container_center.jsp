<%@page import="it.unical.uniexam.hibernate.domain.Department"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("departmentButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.department'/></legend>
		<ol id="#sorting">
		<%
			Set<Department> depatments = (Set<Department>) request.getAttribute("departments");
			if(depatments!=null && !depatments.isEmpty()) {
				for(Department d : depatments) {
		%>
					<li class="list-item" style="border-radius: 4px; ">
						<article>
							<section id="<%="department" + d.getId()%>">
								<span class="span_expandible" id="collapsedivrse<%=d.getId()%>" onclick="collapseMok(this); getDataFromAjax('department/department_details','<%=d.getId()%>','divrse<%=d.getId()%>');">+</span><%=d.getName()%>
								<div id="divrse<%=d.getId()%>" style="display: none;"></div>
							</section>
						</article>
						<br>
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
