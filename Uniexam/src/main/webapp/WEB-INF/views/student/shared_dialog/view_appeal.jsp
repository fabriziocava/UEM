<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
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
	$(document).ready(function(){
		dialogViewAppeal();
		initCollapsable();
	});
</script>

<script type="text/javascript">
function inscribeToAppeal(id){
	$("<div>Vuoi iscriverti?</div>").attr('id',"divInscribe").appendTo('body');
 	$("#divInscribe").attr("title",'Inscribe?');
	$("#divInscribe").dialog({
 	      resizable: false,
 	      modal: true,
 	      buttons: {
	        "Confirm": function() {
 	        	var ajax=sendAJAXmessage($("#context").attr("value")+"/ajax/appeal/inscribeToAppeal","GET","id",id);
 	        	ajax.done(function(msg){
 	        		if(msg.match("no")){
 	        			alert("Errore! Iscrizione non riuscita");
 	        		}else{
 	        			alert("Iscrizione avvenuta correttamente");
	        			window.location=$("#context").attr("value")+"/course";
	        		}
	        	});
	         	$( this ).dialog( "close" );
	         	$("div").remove("#divInscribe");
	        },
	        Cancel: function() {
	          	$( this ).dialog( "close" );
	        	$("div").remove("#divInscribe");
	        }
	      },
	      close:function(){
				$( this ).dialog( "close" );
				$("div").remove("#divInscribe");
			}
	    });
	$("#divInscribe").attr("title","");
}
</script>

<div id="dialog_content">
	<%
	Student student = (Student) request.getAttribute("I");
	ArrayList<Appeal> appeal = (ArrayList<Appeal>) request.getAttribute("appeal");
	ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
	boolean isInscribed = false;
	if(appeal!=null && !appeal.isEmpty()) {
		%>
		<table border="1">
			<thead>
				<tr>
	<%-- 				<th><spring:message --%>
	<%-- 						code='message.professor.appeal.add_appeal.name' /></th> --%>
					<th>Prova</th>
					<th>Data Esame</th>
					<th>Nr. iscritti</th>		
					<th>Iscritto</th>
					<th><th>
				</tr>
				<%
				for(Appeal a : appeal) {
					%>
					<tr>
						<td><%=a.getName()%></td>
						<td align="center"><%=a.getExamDate()%></td>
						<td align="center"><%=a.getCurrNumberOfInscribed()%>/<%=a.getMaxNumberOfInscribed()%></td>
						<%
						if(appealStudent!=null && !appealStudent.isEmpty()) {
							for(AppealStudent as : appealStudent) {
								if(a.getId()==as.getAppeal().getId()) {
									if(as.getStudent().getId()==student.getId())
										isInscribed=true;
								}
							}					
						}
						if(isInscribed) {
						%>
							<td align="center">SI</td>
							<td rowspan="2"><a class="bottonmok" href="" onclick="inscribeToAppeal('<%=a.getId()%>')">Cancella</a></td>
						<%
						} else {
							%>
							<td align="center">NO</td>
							<td rowspan="2"><a class="bottonmok" href="" onclick="inscribeToAppeal('<%=a.getId()%>')">Iscrivi</a></td>
						<%							
						}
						%>
					</tr>
					<tr>
						<td colspan="4"><%=a.getDescription()%></td>
					</tr>					
					<tr>
						<td colspan="5"></td>
					</tr>
					<%
				}
				%>
				</thead>
			</table>
			<%
			} else {
				%>
				<label>Non ci sono appelli</label>
				<%
			}
			%>
</div>