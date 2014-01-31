<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
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
	$(document).ready(function(){
		dialogViewAppeal();
		initCollapsable();
	});
</script>
<div id="dialog_content">
	<%
	ArrayList<Appeal> appeal = (ArrayList<Appeal>) request.getAttribute("appeal");
	if(appeal!=null && !appeal.isEmpty()) {
		%>
		<table border="1">
			<thead>
				<tr>
	<%-- 				<th><spring:message --%>
	<%-- 						code='message.professor.appeal.add_appeal.name' /></th> --%>
					<th>Prova</th>
					<th>Data Esame</th>
					<th>Nr. iscritti</th>		
					<th>Iscritto</th>
					<th><th>
				</tr>
				<%
				for(Appeal a : appeal) {
					%>
					<tr>
						<td><%=a.getName()%></td>
						<td><%=a.getExamDate()%></td>
						<td><%=a.getCurrNumberOfInscribed()%>/<%=a.getMaxNumberOfInscribed()%></td>
						<td>SI/NO</td>
						<td rowspan="2"><a class="bottonmok" href="" >Iscriviti</a></td>
					</tr>
					<tr>
						<td colspan="4"><%=a.getDescription()%></td>
					</tr>					
					<tr>
						<td colspan="5"></td>
					</tr>
					<%
				}
				%>
				</thead>
			</table>
			<%
			} else {
				%>
				<label>Non ci sono appelli</label>
				<%
			}
			%>
</div>