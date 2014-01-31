<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript">
	$(document).ready(function(){
		dialogAddCourse();
	});
		
		
		function accoppia(){
		$("#codice_id").attr("value",$("#codice_id_realy").val());
		$("#name_id").attr("value",$("#name_id_realy").val());
		$("#credits_id").attr("value",$("#credits_id_realy").val());
		
	}
	
</script>


<div id="dialog_content">
<form:form action="${pageContext.request.contextPath}/manager/course/add" id="add_course_form"
 modelAttribute="addcourse" method="post" onsubmit="accoppia()">
 
<div style="display: none;">
	 <form:input id="codice_id" path="code" />
	<form:input id="name_id" path="name" />
	<form:input id="credits_id" path="credits" />
</div>

<table class="tablemok">

<tr><td><label title="<spring:message code='message.manager.course.codice'/>" ><spring:message code="message.manager.course.codice" /></label></td><td>
<input id="codice_id_realy" type="text" placeholder="<spring:message code="message.manager.course.codice" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.manager.course.nome'/>" ><spring:message code="message.manager.course.nome" /></label></td><td>
<input id="name_id_realy" type="text" placeholder="<spring:message code="message.manager.course.nome" />" required/></td></tr>

<tr><td><label title="<spring:message code='message.manager.course.crediti'/>" ><spring:message code="message.manager.course.crediti" /></label></td><td>
<input id="credits_id_realy" type="text" placeholder="<spring:message code="message.manager.course.crediti" />" required/></td></tr>

<tr>
<td><form:label path="degreeCourse">Corso di Laurea</form:label></td>
<td><form:select path="degreeCourse" items="${model.degreecourses}" itemValue="id" itemLabel="name"></form:select></td>
</tr>




<tr>
</table>

<input type="submit" id="submit" value="Submit"> 
 
 </form:form>

</div>