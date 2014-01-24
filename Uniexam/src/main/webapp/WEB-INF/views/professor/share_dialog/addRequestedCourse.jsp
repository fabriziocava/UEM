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
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>

<% %>
<% %>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dialogAddRequested").attr("title","Add Requested Course");
		$("#dialogAddRequested").dialog({
			autoOpen : true,
			modal: true,
			width:"auto",
			show : {
				effect : "blind",
				duration : 500
			},
			hide : {
				effect : "explode",
				duration : 500
			},
			close:function(){
				$( this ).dialog( "close" );
				$("div").remove("#dialogAddRequested");
			},
			buttons:{
				Save:function(){
					//something else
					if($("input[name^='policyOfRequested']:radio:checked").val()!=undefined){
//							alert("vado");
						submitAddRequestedCourse();
						$( this ).dialog( "close" );
						$("div").remove("#dialogAddRequested");
					}else{
						alert("choose a policy");
					}
				}
			}
		});
		$("#dialogAddRequested").attr("title","");
	});
// 	function submitFormAddRequested(form){
// 		if(form.path.policyOfRequested.value!=null){
// 			("#dialogAddRequested").dialog("close");
// 			$("div").remove("#dialogAddRequested");
// 			form.submit();
// 		}else
// 			alert("choose a policy");
// 	}
</script>

<div id="dialog_content">
<form:form id="addrequestedCourse" modelAttribute="requestedCourse" method="post" action="addRequestedCourseAction">
<table class="tablemok">
<tr>
<td><form:label path="policyOfRequested">Degree of requested</form:label></td>
<td><form:radiobuttons id="policyOfRequested" path="policyOfRequested" items="${model.degree}"/></td>
</tr>
<tr>
<td><form:label path="Course">Course</form:label></td>
<td><form:select path="Course" items="${model.courses}" itemLabel="name"></form:select></td>
<td><input type="hidden" name="idCourse" value="${model.idCourse}"/></td>
</tr>
<tr><td></td><td>
<!-- <input type="submit" value="Register" /> -->
</td></tr>
</table>
</form:form>
<br>
</div>









