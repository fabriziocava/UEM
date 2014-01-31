<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor "%>
<%@page import="java.util.Set"%>
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
	
			
		});
		$("#dialogAddRequested").attr("title","");
	});
		
	
</script>
<%
Set<Professor> professor=(Set<Professor>)request.getAttribute("professors");
Long c =(Long) request.getAttribute("idcourse");

%>


<div id="dialog_content">
<form id=" setholder " action="${pageContext.request.contextPath}/manager/assegnaCorsoAction"  method="post" >
<table class="tablemok">

<tr>

<td><label >Assegna Corso a Professore</label></td>

<td><select name="idprofessor"> 
<%for(Professor p:professor){	%>
<option   value="<%=p.getId() %>"  >  <%= p.getName() + " "+ p.getSurname() %>  </option>       

<%} %>

 </select></td>

<td><input type="hidden" name="idCourse" value="<%=c %>"/></td>


</tr>

<tr><td></td><td>
</td></tr>
</table>

<input type="submit" id="submit" value="Salva"> 


</form>
<br>

</div>











