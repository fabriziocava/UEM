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

<% Appeal appeal=(Appeal)request.getAttribute("appeal"); %>

<script type="text/javascript">
	$(document).ready(function(){
		
		<%Boolean ris=(Boolean)request.getAttribute("ris");
		if(ris!=null && ris){%>
		alert("Modifiche apportate con successo");
		<%}else if(ris!=null && !ris){%>
		alert("Errore nell'apportare le modifiche");
		<%}%>
		dialogViewListStudent();
		$('.titlemok').each(function(item,element){
			titlemok('square-small', element.id);
		});
		
		$("#tableSortable").tablesorter();
	});
	
	function removeStudent(id){
		$('<div></div>')
		.attr('id','flashDialog')
		.attr('title','remove student?')
		.appendTo('body');
		$('#flashDialog').dialog({
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
				$("div").remove("#flashDialog");
			},
			buttons:{
				"Confirm":function(){
					var conte=$("#context").attr("value");
					var dati=new FormData();
					dati.append("idAppealStudent",id);
					dati.append("idAppeal",'<%=appeal.getId()%>');
					var ing=$.ajax({
						url: conte+'/ajax/appeal/remove_student',
						type: "POST",
						data:dati,
						processData: false,
						contentType: false
					});
					ing.done(function(data){
						if($("#dialog").html()==undefined)
							$("<div></div>").attr('id','dialog').appendTo('body');
						$("#dialog").html(data);
					});
					
					$( this ).dialog( "close" );
					$("div").remove("#flashDialog");
				},
				"Cancel":function(){
					$( this ).dialog( "close" );
					$("div").remove("#flashDialog");
				}
			}
		});
	}
</script>

<div id="dialog_content">

		<%
			ArrayList<ArrayList<Object>> appealStudentsRegAndNot = (ArrayList<ArrayList<Object>>) request
					.getAttribute("appealstudentsRegAndNot");
		ArrayList<Object> appealStudents = appealStudentsRegAndNot.get(0);
		ArrayList<Object> appealStudentsNoRegular = appealStudentsRegAndNot.get(1);
		
		
		%>
		<div><%=appeal.getName()%>
			|
			<%=appeal.getLocation()%>
			<br />
			<%=appeal.getDescription()%></div>
<fieldset>
		<legend>
			<spring:message code="message.professor.course.container.center.legendCourses" />
		</legend>
		<table class="tablemok" id="tableSortable">
		<thead>
			<tr style="text-align: center;">
				<th style="padding: 0px 20px 0px 20px;">Stato</th>
				<th style="padding: 0px 20px 0px 20px;">Matricola</th>
				<th style="padding: 0px 20px 0px 20px;">Nome</th>
				<th style="padding: 0px 20px 0px 20px;">Voto</th>
				<th style="padding: 0px 20px 0px 20px;">Nota</th>
				<th style="padding: 0px 20px 0px 20px;">Elimina</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (appealStudents != null && appealStudents.size() > 0) {
				for (Object appObj : appealStudents) {
					AppealStudent app=(AppealStudent)appObj;
			%>
			<tr class="line-top" style="text-align: center;">
				<td></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getSerialNumber()%></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getName()%> <%=app.getStudent().getSurname()%></td>
				<td style="padding: 0px 20px 0px 20px;" 
				contenteditable="true" 
				onfocus="storeOld(this)" 
				onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','temporany_vote','D')">
				<%=app.getTemporany_vote()%></td>
				<td style="padding: 0px 20px 0px 20px;"
				contenteditable="true" 
				onfocus="storeOld(this)" 
				onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','note','S')">
				<%=app.getNote()%></td>
				<td style="padding: 0px 20px 0px 20px;"><a class="img-active icon icon-trash clickable" onclick="removeStudent('<%=app.getId()%>')">re</a></td>
			</tr>
			<%} %>
			<%}else{%>
<!-- 				No Regular students -->
			<%} %>
			<% 
				if(appealStudentsNoRegular!=null && appealStudentsNoRegular.size()>0){
// 					ArrayList<ArrayList<RequestedCourse>>requestedCourses=(ArrayList<ArrayList<RequestedCourse>>)request.getAttribute("requestedCourses");
					for (Object appAO : appealStudentsNoRegular) {
						
						ArrayList<Object> appO=(ArrayList<Object>)appAO;
						
						AppealStudent app=(AppealStudent)appO.get(0);
						ArrayList<RequestedCourse> requestedCourses=(ArrayList<RequestedCourse>)appO.get(1);
						
						String policy=RequestedCourse.POLICY_LIGHT;
						for(RequestedCourse req:requestedCourses){
							if(req.getPolicyOfRequested().equals(RequestedCourse.POLICY_MEDIUM)){
								policy=RequestedCourse.POLICY_MEDIUM;
							}
							if(req.getPolicyOfRequested().equals(RequestedCourse.POLICY_STRONG)){
								policy=RequestedCourse.POLICY_STRONG;
								break;
							}
						}
							
			%>
			<tr class="line-top" style="text-align: center;">
				<td style="display: inline-block">
				<div class="square-small <%=policy%>" onclick="openDiv('titleN<%=app.getStudent().getSerialNumber()%>')"></div>
				<div style="display: none;" class="titlemok" id="titleN<%=app.getStudent().getSerialNumber()%>">
					<buttonmok onclick="closeDiv(this)" class="closeButton">X</buttonmok>
						<table class="tablemok" >
							<tr><th>Lo studente non ha sostenuto i seguenti insegnamenti richiesti:</th></tr>
							<%for(RequestedCourse r:requestedCourses){ %>
							<tr><td>
							<%=r.getCourse().getName() %>
							</td></tr>
							<%} %>
						</table>
				</div>
				</td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getSerialNumber()%></td>
				<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getName()%> <%=app.getStudent().getSurname()%></td>
				<td style="padding: 0px 20px 0px 20px;" 
				contenteditable="true" 
				onfocus="storeOld(this)" 
				onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','temporany_vote','D')">
				<%=app.getTemporany_vote()%></td>
				<td style="padding: 0px 20px 0px 20px;"
				contenteditable="true" 
				onfocus="storeOld(this)" 
				onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','note','S')">
				<%=app.getNote()%></td>
				<td style="padding: 0px 20px 0px 20px;"><a class="img-active icon icon-trash clickable" onclick="removeStudent('<%=app.getId()%>')">re</a></td>
			</tr>
			<%}
					} else{%>
<!-- 					No irregular student -->
					<%} %>
			</tbody>
		</table>
					<div class="line-top"></div>
					<buttonmok onclick="openAddStudentToAppeal(<%=appeal.getId()%>)">
					Aggiungi studente					
					</buttonmok>

	</fieldset>






<!-- <td style="padding: 0px 20px 0px 20px;"><a class="icon img-active icon-disk clickable">a</a></td> -->
<!-- 				<td style="padding: 0px 20px 0px 20px;"><a class="icon img-unactive icon-trash clickable">a</a></td> -->









</div>