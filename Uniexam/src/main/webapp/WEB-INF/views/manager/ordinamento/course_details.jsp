<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
	<%
		Course c=(Course)request.getAttribute("course");
	%>

<div id="course_details_<%=c.getId()%>" style="display: none;">
	<script type="text/javascript">
	$(document).ready(function(){
		//go this in fade in
		$("#course_details_<%=c.getId()%>").slideDown(1000);
	});
	
	
	function sendDeleteAppeal(id,id2){
		$("<div>Sei sicuro di voler questo corso ?</div>").attr('id',"divDelete").appendTo('body');
		$("#divDelete").attr("title",'Elimina Corso');
		$("#divDelete").dialog({
		      resizable: false,
		      modal: true,
		      buttons: {
		        "Conferma": function() {
		        	var ajax=sendAJAXmessagePino($("#context").attr("value")+"/ajax/course/remove_courseALL","GET","id",id,"id2",id2);
		        	ajax.done(function(msg){
		        		if(msg.match("no")){
		        			alert("Errore! non ho potuto eliminare il corso, corso propedeutico ad altro corso");
		        		}else{
		        			alert("Corso eliminato");
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
	<table class="tablemok">
	<tbody>
	
	<tr>
	<th>
	<spring:message code="message.manager.course.removecourse" />
	</th>
	<td><a class="bottonmok color_red" onclick="sendDeleteAppeal('<%=c.getId()%>','<%=c.getDegreeCourse().getId() %>')"><spring:message code='message.general.remove' /></a></td>
	
	</tr>
	
	<tr>
	</tbody>
	</table>
</div>
