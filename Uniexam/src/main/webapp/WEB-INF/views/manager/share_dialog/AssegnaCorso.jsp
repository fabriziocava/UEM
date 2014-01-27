<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
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
		dialogAssegnaCorso();
		dialogModifyCourse();
			
	});

		
	
</script>
<%
	Course c = (Course) request.getAttribute("course");
%>
<div id="dialog_content">
	
	<h1 align="center"><%=c.getName()%></h1>

</div>

<table class="tablemok">
<tr>
<%if(c.getHolder()!=null){ %>
<th><%=c.getHolder().getName() + " " + c.getHolder().getSurname() %></th>
<td><div class="line-top"></div></td>
<td><div class="bottonmok color_red" onclick=""><spring:message code="message.general.delete" /></div></td>
<% } else { %>
<td><div class="line-top"></div></td>
	 <td><%= "Corso non assegnato" %> </td> 
<td><div class="bottonmok " id="addRequested<%=c.getId()%>"><spring:message code="message.general.add" /></div></td>
<%} %>
</tr>
</table>









