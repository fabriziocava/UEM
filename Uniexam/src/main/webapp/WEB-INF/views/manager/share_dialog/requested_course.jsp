<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		dialogModifyRequestedCourse();
		<%String res=((String) request.getAttribute("result"));
		if(res!=null){
			if(res.equals("success")){%>
				alert("Modifiche apportate!");
			<%}else{%>
				alert("Error in some istruction!");
			<%}}%>
			titlemok('square-small', "legendOfDegree");
	
			
	});

	function sendDeleteAppeal(id,id2){
		$("<div>Sei sicuro di voler eliminare la propedeuticita' ?</div>").attr('id',"divDelete").appendTo('body');
		$("#divDelete").attr("title",'Elimina Propedeuticità');
		$("#divDelete").dialog({
		      resizable: false,
		      modal: true,
		      buttons: {
		        "Conferma": function() {
		        	var ajax=sendAJAXmessagePino($("#context").attr("value")+"/ajax/course/remove_course","GET","id",id,"id2",id2);
		        	ajax.done(function(msg){
		        		if(msg.match("no")){
		        			alert("Errore! non ho potuto eliminare la propedeuticita'");
		        		}else{
		        			alert("Propedeuticita' eliminata");
		        			window.location=$("#context").attr("value")+"/course/list";
		        		}
		        	});
		         	$( this ).dialog( "close" );
		         	$("div").remove("#divDelete");
		        },
		        Cancella: function() {
		          	$( this ).dialog( "close" );
		        	$("div").remove("#divDelete");
		        }
		      },
		      close:function(){
					$( this ).dialog( "close" );
					$("div").remove("#divDelete");
				}
		    });
		$("#divDelete").attr("title","");
	}

	
	
	
</script>
<%
	Course c = (Course) request.getAttribute("course");
%>
<div id="dialog_content">
	
	<h1 align="center"><%=c.getName()%></h1>
	<div>
		<table class="tablemok">
			<%
				for(RequestedCourse req:c.getRequestedCourses()){
			%>
		<tr>
		<td>
		<div>
		<div class="line-top"></div>
		<div class="square-small <%=req.getPolicyOfRequested()%>"></div>
		<div id="requestedCourse<%=req.getCourse().getId()%>"><%=req.getCourse().getName()%></div>
		<ul class="links-user">
		<li><spring:message code='message.general.options' /> :</li> 
		<li class="bottonmok color_red" onclick="sendDeleteAppeal('<%=c.getId()%>','<%=req.getId()%>')"><spring:message code='message.general.remove' /></li>
		</ul>
		</div>
		</td>
		</tr>
			<%}
				
			%>
			<tr>
			<td>
			<div class="line-top"></div>
			</td>
			</tr>
			<tr>
			<td>
			<div class="line-top"></div>
			<div class="bottonmok" id="addRequested<%=c.getId()%>"><spring:message code='message.general.add' /></div>
			</td>
			</tr>
			<%
				
			%>
<!-- 			<tr> -->
<!-- 			<td> -->
<!-- 			<div class="line-top"></div> -->
<!-- 			</td> -->
<!-- 			</tr> -->
		</table>
	</div>

</div>

<div id="radiostrong" style="display: none;">
<label><input type='radio' name='choose' value="light"/>light</label>
<label><input type='radio' name='choose' value="medium"/>medium</label>
</div>

<div id="radiolight" style="display: none;">
<label><input type='radio' name='choose' value="medium"/>medium</label>
<label><input type='radio' name='choose' value="strong"/>strong</label>
</div>

<div id="radiomedium" style="display: none;">
<label><input type='radio' name='choose' value="light"/>light</label>
<label><input type='radio' name='choose' value="strong"/>strong</label>
</div>

<!-- <td> -->
<%-- 		<span class="span_expandible" id="collapserequestCourse<%=req.getCourse().getId()%>">+</span> <%=req.getCourse().getName()%> --%>
<!-- 		</td></tr> -->
<%-- 		<tr id="requestCourse<%=req.getCourse().getId()%>" style="display: none;"><td> --%>
<!-- 		<div> -->
<!-- 		<div>Quadretto in base al grado</div> -->
<%-- 		<%req.getPolicyOfRequest(); %>///****** --%>
<%-- 		<span class="span_expandible" id="collapserequestCourseOption<%=req.getCourse().getId()%>">+</span> <%=req.getCourse().getName()%> --%>
		
<!-- 		</div> -->
<!-- 		</td> -->










