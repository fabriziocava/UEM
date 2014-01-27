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
<script type="text/javascript">
	$(document).ready(function() {
		$("#dialogAddRequested").attr("title","Assegna Corso");
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
					
//							alert("vado");
						submitAssignCourse();
						$( this ).dialog( "close" );
						$("div").remove("#dialogAddRequested");
								}
						
					
					}
			
		});
		$("#dialogAddRequested").attr("title","");
	});
		
	
</script>
<%
	
%>
<div id="dialog_content">
<form:form id=" addrequestedCourse " action="${pageContext.request.contextPath}/manager/assegnaCorsoAction" modelAttribute="assegnaCorso" method="post" >
<table class="tablemok">

<tr>
<td><form:label path="holder">Assegna corso a professore</form:label></td>
<td><form:select path="holder" items="${model.professors}" itemLabel="name"  itemValue="id"></form:select></td>
<td><input type="hidden" name="idCourse" value="${model.idcourse}"/></td>
</tr>
<tr><td></td><td>
<!-- <input type="submit" value="Register" /> -->
</td></tr>
</table>



</form:form>
<br>

</div>











