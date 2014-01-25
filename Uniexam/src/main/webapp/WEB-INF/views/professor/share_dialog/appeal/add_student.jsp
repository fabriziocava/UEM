<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>

<% String appeal=(String)request.getAttribute("idAppeal"); 
	%>
<% %>
<script type="text/javascript">
	function aggiungi(){
		var idStudent=$("#studentID").attr('value');
		if(idStudent!=undefined){
			var idAppeal='<%=appeal%>';
			var conte=$("#context").attr("value");
			var dati=new FormData();
			dati.append("idAppeal",idAppeal);
			dati.append("idStudent",idStudent);
// 			sendAJAXmessage2(conte+"/ajax/appeal/add_student", "POST", dati);
// 			var aj=sendAJAXmessage2(conte+"/ajax/appeal/add_student", "POST", dati);
// 			aj.done(function(msg){
// 				if(msg.match("ok"))
// 					alert("Aggiunto");
// 				else
// 					alert("Errore nell'aggiunzione");
// 			});
// 			aj.error(function(){
// 				alert("Errore nell'aggiunzione");
// 			});
// 			var ajax=$.post(conte+'/ajax/appeal/add_student', dati.serialize());
			var ing=$.ajax({
				url: conte+'/ajax/appeal/add_student',
				type: "POST",
				data:dati,
				processData: false,
				contentType: false
			});
			$(".processing").css("display","block");
			ing.done(function(msg){
				$(".processing").css("display","none");
			});
			ing.done(function(data){
				if($("#dialog").html()==undefined)
					$("<div></div>").attr('id','dialog').appendTo('body');
				$("#dialog").html(data);
			});
// 			$( this ).dialog( "close" );
			return true;
		}else{
			alert("Devi selezionare uno studente per fare una aggiunzione");
			return false;
		}
	}
	function selemok(item){
		$(item).parent().css('background-color','red');
	}
	function deselemok(item){
		$(item).parent().css('background-color',' white');
	}
	function selectmok(item){
		$(item).parent().css('background-color',"green");
		$("input[name='matricola']").val($(item).children().html());
		$("#studentID").attr('value',item.id);
		$("#complete").delay(200).html("");
	}
</script>

<div id="dialog_content">
<input type="text" min="3" maxlength="8" 
name="matricola" id="idComplete" 
ondblclick="ajaxAutoComplete()"/>
<input id="studentID" name="studentID" type="hidden"/>
<!-- <div id="complete" style="position: fixed"></div> -->
<!-- <div style="position: fixed;"> -->
<div>
<table class="tablemok" style="background-color: white; display:table-row-group" id="complete">
</table>
</div>
</div>









