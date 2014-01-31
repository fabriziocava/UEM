<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="java.util.Set"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("groupButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.groups'/></legend>
		<ol id="#sorting">
			<%
			Set<Group> groups = (Set<Group>) request.getAttribute("groups");
			if(groups!=null && !groups.isEmpty()) {
				for(Group g : groups) {
					%>
					<li class="list-item" style="border-radius: 4px; ">
						<article>
							<section id="<%="group" + g.getId()%>">
								<span class="span_expandible" id="collapsedivrse<%=g.getId()%>" onclick="">+</span><%=g.getName()%>
								<div id="divrse<%=g.getId()%>" style="display: none;"></div>
							</section>
						</article>
						<br>
					</li>
					<%
				}
			}
			else {
				%>
				Non ci sono gruppi
				<%	
			}
			%>
		</ol>
	</fieldset>
	
</div>