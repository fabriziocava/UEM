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
<%Appeal appeal=(Appeal)request.getAttribute("appeal"); %>
<div id="dialog_content">
<table class="tablemok">
<tr><td title="<spring:message code='message.professor.appeal.add_appeal.name.description'/>">
<div id="name_id_realy">
<span class="span_expandible" id="collapsedivappealdetails" onclick="getDataFromAjax('appeal/appeal_details','<%=appeal.getId()%>','divappealdetails');">+</span>
<%=appeal.getName() %>
<div id="divappealdetails" style="display: none;"></div>
</div>
</td></tr>
</table>

<table class="tablemok">
<tr>
<th>Studenti iscritti a questo appello</th>
<td><%=appeal.getAppeal_student().size() %></td>
<td><a class="bottonmok" href="${pageContext.request.contextPath}/professor/appeal/list_student?appeal=<%=appeal.getId() %>" target="black">vedi lista</a></td>
</tr>
</table>
















</div>