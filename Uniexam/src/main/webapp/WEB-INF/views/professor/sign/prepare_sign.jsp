<%@page import="org.springframework.validation.BindingResult"%>
<%@page import="java.util.Enumeration"%>
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
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("signButton"));
	});
</script>
<style>
.errore{
color: red;
}
</style>
<div class="container-center">
<div class="aligncenter">
<h2>Sign Single</h2>
</div>
<div class="aligncenter">
	<script type="text/javascript">
		$(document).ready(function() {
			
			<%
			Boolean ris=(Boolean)request.getAttribute("ris");
			if(ris!=null)
			if(ris){
			%>
			alert("<spring:message code='message.professor.modify.success'/>");
			<%
			}else{
			%>	
			alert("<spring:message code='message.professor.modify.error'/>");
			<%}
			%>
		});
		function accoppia(){
// 			if($("#studentID").val()==""){
// 				delayCssProp('idCompleteStudent', 5000, 'border','4px solid red');
// 				alert("Selezionare uno studente!");
// 				return false;
// 			}
// 			if(parseInt($("#temporany_vote_realy").val())<18 || parseInt($("#temporany_vote_realy").val())>31){
// 				delayCssProp('temporany_vote_realy', 5000, 'border','4px solid red');
// 				alert("Il voto deve essere compreso tra 18 e 31");
// 				return false;
// 			}
			$("#student_id").attr("value",$("#studentID").val());
			$("#temporany_vote_id").attr("value",$("#temporany_vote_realy").val());
			$("#note_id").attr("value",$("#note_realy").val());
			return true;
		}
		
	</script>

<form:form modelAttribute="appealStudent" method="post" action="add_single_sign" onsubmit="return accoppia()">
<div class="startHide">
<form:input id="student_id" path="student" />
<form:input id="temporany_vote_id" path="temporany_vote" />
<form:input id="note_id" path="note" />
</div>
<%
BindingResult res=(BindingResult)request.getAttribute("org.springframework.validation.BindingResult.appealStudent");
if(res!=null && res.hasErrors()){ %>
<div class="aligncenter">
<ul class="errore">
<li >
<form:errors path="student"  />
</li><li>
<form:errors path="temporany_vote"  />
</li>
</ul>
</div>
<% }%>
<table class="tablemok">
<tr>
<td><form:label path="student">Student</form:label></td>
<td>
<div>
	<input type="text" min="3" maxlength="8" id="idCompleteStudent"
		placeholder="Matricola, Name"
		onkeyup="ajaxAutoComplete('/ajax/sign/autocomplete/students',this.value,'risTable')"
		onblur="clearContent('risTable')"
		autocomplete="off"/>
	<input id="studentID" type="hidden" required/>
	<div>
		<table class="table-list tablemok"
			style="background-color: white; display: table-row-group"
			id="risTable">
		</table>
	</div>
</div>
</td>
</tr>
<tr>
<td><form:label path="Course">Course</form:label></td>
<td><form:select path="Course" items="${model.courses}" itemLabel="name"></form:select></td>
<td><input type="hidden" name="idCourse" required ></td>
</tr>
<tr>
<td><form:label path="temporany_vote">Vote</form:label></td>
<td><input type="number" id="temporany_vote_realy" value="18" ></td>
</tr>
<tr>
<td><form:label path="date">Date</form:label></td>
<td><form:input path="date" type="date" id="date_realy" required="required"/></td>
</tr>
<tr>
<td><form:label path="note">Note</form:label></td>
<td><input type="text" id="note_realy" value="" placeholder="Note"/></td>
</tr>
</table>
<input type="submit" value="Prepare">
</form:form>
	
</div>
</div>
