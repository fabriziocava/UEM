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
		selectingFromDashBoard(document.getElementById("verbalToBeSignedButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.verbalToBeSigned'/></legend>
		<ol id="#sorting">
			
		</ol>
	</fieldset>
	
</div>