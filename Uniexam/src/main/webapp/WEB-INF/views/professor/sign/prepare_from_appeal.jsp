<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->

<%
		ArrayList<AppealStudent> appealStudents=(ArrayList<AppealStudent>) request.getAttribute("appealStudents");
		Appeal appeal=(Appeal) request.getAttribute("appeal");
		if(appealStudents!=null && appealStudents.size()>0){
	%>

<% %>
<div class="aligncenter"><%=appeal.getName() %></div>
<%
}else{%>
Errore!
<%}%>