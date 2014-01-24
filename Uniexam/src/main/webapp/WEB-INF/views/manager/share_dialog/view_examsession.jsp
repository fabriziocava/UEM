<%@page import="javassist.expr.NewArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.ExamSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    
    

<script type="text/javascript">
	$(document).ready(function(){
		dialogViewExamSession();
	});
	
	function sendDeleteAppeal(id){
		$("<div>Sei sicuro di voler eliminare la sessione ?</div>").attr('id',"divDelete").appendTo('body');
		$("#divDelete").attr("title",'Elimina Sessione');
		$("#divDelete").dialog({
		      resizable: false,
		      modal: true,
		      buttons: {
		        "Conferma": function() {
		        	var ajax=sendAJAXmessage($("#context").attr("value")+"/ajax/exam_session/remove_session","GET","id",id);
		        	ajax.done(function(msg){
		        		if(msg.match("no")){
		        			alert("Errore! non ho potuto eliminare la sessione");
		        		}else{
		        			alert("Sessione eliminata");
		        			window.location=$("#context").attr("value")+"/exam_session";
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





<div id="dialog_content">

<script type="text/javascript">

</script>

<%ExamSession examsession=(ExamSession)request.getAttribute("examsession"); %>

<table class="tablemok">
<tr>
<th><%=examsession.getDescription() %></th>
<td><div class="line-top"></div></td>
<td><div class="bottonmok color_red" onclick="sendDeleteAppeal('<%=examsession.getId()%>')"><spring:message code="message.general.delete" /></div></td>

</tr>
</table>
</div>

<table class="tablemok">

<tr><td>
<div id="name_id_realy">
<span class="bottonmok" id="collapsedivappealdetails"      onclick="getDataFromAjax('exam_session/sessionDetails','<%=examsession.getId()%>','divappealdetails'); collapseMok(this)">Modifica</span>

<div id="divappealdetails" style="display: none;"></div>
</div>
</td></tr>
</table>



