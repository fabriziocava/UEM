<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript">
	$(document).ready(function(){
		dialogAddSession();
		$(function() {
		    $( "#openDatesession_id_realy" ).datetimepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      dateFormat: 'dd-mm-yy',
		      onClose: function( selectedDate ) {
		        $( "#closeDatesession_id_realy" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
		    $( "#closeDatesession_id_realy" ).datetimepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      dateFormat: 'dd-mm-yy',
		      onClose: function( selectedDate ) {
		        $( "#openDatesession_id_realy" ).datepicker( "option", "maxDate", selectedDate );
		      }
		    });
		    
		});
	});
	function accoppia(){
		$("#openDatesession_id").attr("value",$("#openDatesession_id_realy").val());
		$("#closeDatesession_id").attr("value",$("#closeDatesession_id_realy").val());
		$("#descriptionsession_id").attr("value",$("#descriptionsession_id_realy").val());
	}
	
</script>


<div id="dialog_content">
<form:form action="${pageContext.request.contextPath}/manager/exam_session/add" id="add_session_form"
 modelAttribute="addsession" method="post" onsubmit="accoppia()">
 
<div style="display: none;">
	 <form:input id="descriptionsession_id" path="description" />
	<form:input id="openDatesession_id" path="openDate" />
	<form:input id="closeDatesession_id" path="closeDate" />
</div>

<table class="tablemok">

<tr><td><label title="<spring:message code='message.manager.examsession.description.description'/>" ><spring:message code="message.manager.examsession.description" /></label></td><td>
<input id="descriptionsession_id_realy" type="text" placeholder="<spring:message code="message.manager.examsession.description" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.manager.examsession.startdate.description'/>" ><spring:message code="message.manager.examsession.startdate" /></label></td><td>
<input id="openDatesession_id_realy" type="text" placeholder="<spring:message code="message.manager.examsession.startdate" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.manager.examsession.enddate.description'/>" ><spring:message code="message.manager.examsession.enddate" /></label></td><td>
<input id="closeDatesession_id_realy" type="text" placeholder="<spring:message code="message.manager.examsession.enddate" />" required/></td></tr>

<tr>
<td><form:label path="course">Corso di Laurea</form:label></td>
<td><form:select path="course" items="${model.degreecourses}" itemValue="id" itemLabel="name"></form:select></td>
</tr>

<tr>
</table>

<input type="submit" id="submit" value="Submit"> 
 
 </form:form>

</div>