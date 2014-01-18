<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	$(document).ready(function(){
		dialogAddAppeal();
// 		$( "#openDate_id_realy" ).datetimepicker();
		$(function() {
		    $( "#openDate_id_realy" ).datetimepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      dateFormat: 'dd-mm-yy',
		      onClose: function( selectedDate ) {
		        $( "#closeDate_id_realy" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
		    $( "#closeDate_id_realy" ).datetimepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      dateFormat: 'dd-mm-yy',
		      onClose: function( selectedDate ) {
		        $( "#openDate_id_realy" ).datepicker( "option", "maxDate", selectedDate );
		        $( "#examDate_id_realy" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
		    $( "#examDate_id_realy" ).datetimepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      numberOfMonths: 1,
			      dateFormat: 'dd-mm-yy',
			      onClose: function( selectedDate ) {
			        $( "#closeDate_id_realy" ).datepicker( "option", "maxDate", selectedDate );
			      }
			});
		});
	});
	function accoppia(){
		$("#name_id").attr("value",$("#name_id_realy").val());
		$("#maxNumberOfInscribed_id").attr("value",$("#maxNumberOfInscribed_id_realy").val());
		$("#location_id").attr("value",$("#location_id_realy").val());
		$("#openDate_id").attr("value",$("#openDate_id_realy").val());
		$("#closeDate_id").attr("value",$("#closeDate_id_realy").val());
		$("#examDate_id").attr("value",$("#examDate_id_realy").val());
	}
</script>

<div id="dialog_content">
<!-- <a href="#" onclick="accoppia()">accoppia</a> -->
<form:form action="${pageContext.request.contextPath}/professor/appeal/add" id="add_appeal_form"
 modelAttribute="appeal" method="post" onsubmit="accoppia()">
<div style="display: none;">
<form:input id="name_id" path="name" />
<form:input id="maxNumberOfInscribed_id" path="maxNumberOfInscribed" />
<form:input id="location_id" path="location" />
<form:input id="openDate_id" path="openDate" />
<form:input id="closeDate_id" path="closeDate" />
<form:input id="examDate_id" path="examDate" />
</div>
<table class="tablemok">

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.name.description'/>" ><spring:message code="message.professor.appeal.add_appeal.name" /></label></td><td>
<input id="name_id_realy" type="text" placeholder="<spring:message code="message.professor.appeal.add_appeal.name" />" required /></td></tr>

<tr>
<td><form:label path="course">Course</form:label></td>
<td><form:select path="course" items="${model.courses}" itemValue="id" itemLabel="name"></form:select></td>
</tr>

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.maxNumberOfInscribed.description'/>" ><spring:message code="message.professor.appeal.add_appeal.maxNumberOfInscribed" /></label></td><td>
<input id="maxNumberOfInscribed_id_realy" type="number" placeholder="<spring:message code="message.professor.appeal.add_appeal.maxNumberOfInscribed" />" pattern="\d+" required/></td></tr>

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.location.description'/>" ><spring:message code="message.professor.appeal.add_appeal.location" /></label></td><td>
<input id="location_id_realy" type="text" placeholder="<spring:message code="message.professor.appeal.add_appeal.location" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.openDate.description'/>" ><spring:message code="message.professor.appeal.add_appeal.openDate" /></label></td><td>
<input id="openDate_id_realy" type="text" placeholder="<spring:message code="message.professor.appeal.add_appeal.openDate" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.closeDate.description'/>" ><spring:message code="message.professor.appeal.add_appeal.closeDate" /></label></td><td>
<input id="closeDate_id_realy" type="text" placeholder="<spring:message code="message.professor.appeal.add_appeal.closeDate" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.professor.appeal.add_appeal.examDate.description'/>" ><spring:message code="message.professor.appeal.add_appeal.examDate" /></label></td><td>
<input id="examDate_id_realy" type="text" placeholder="<spring:message code="message.professor.appeal.add_appeal.examDate" />" required/></td></tr>

<tr>
</table>
 <input type="submit" id="submit" value="Submit">
</form:form>
</div>