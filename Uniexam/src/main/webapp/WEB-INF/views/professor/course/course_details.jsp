<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
<div>
THIS IS COURSE DETAILS
	<script type="text/javascript">
	$(document).ready(function(){
		//go this in fade in
	});
	</script>
	<%
	Course c=(Course)request.getAttribute("course"); 
	%>
	<% %>
	<% %>
</div>
