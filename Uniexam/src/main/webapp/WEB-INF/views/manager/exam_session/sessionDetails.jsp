<%@page import="javassist.expr.NewArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.ExamSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%ExamSession examsession=(ExamSession)request.getAttribute("examsession"); %>
<div>

<table>
<tr>
<th><spring:message code='message.manager.examsession.description'/></th><td>
<div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'exam_session/modify_session','<%=examsession.getId()%>','Description','String')">
<%=examsession.getDescription() %></div></td>
</tr>

<tr>
<th><spring:message code='message.manager.examsession.startdate'/></th><td>
<div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'exam_session/modify_session','<%=examsession.getId()%>','dataInizio','Date')">
<%=examsession.getDataInizio() %></div></td>
</tr>

<tr>
<th><spring:message code='message.manager.examsession.enddate'/></th><td>
<div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur=" checkBeforeChangeEditable(this,'exam_session/modify_session','<%=examsession.getId()%>','dataFine','Date')">
<%=examsession.getDataFine() %></div></td>
</tr>

</table>

</div>

