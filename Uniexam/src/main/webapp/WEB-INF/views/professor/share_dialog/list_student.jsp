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
		$(document).ready(function(){
			$('.titlemok').each(function(item,element){
// 				alert(element.id);
				titlemok('square-small', element.id);
			});
<%-- 			titlemok('square-small', "titleN<%=app.getStudent().getSerialNumber()%>"); --%>
		});
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
	function almok(){
		alert("ciao");
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
		<table class="tablemok">
			<tr style="text-align: center;">
				<th style="padding: 0px 20px 0px 20px;"></th>
				<th style="padding: 0px 20px 0px 20px;">Matricola</th>
				<th style="padding: 0px 20px 0px 20px;">Nome</th>
				<th style="padding: 0px 20px 0px 20px;">Voto</th>
				<th style="padding: 0px 20px 0px 20px;">Nota</th>
				<th style="padding: 0px 20px 0px 20px;"></th>
			</tr>
			<%
			if (appealStudents != null && appealStudents.size() > 0) {
				for (Object appObj : appealStudents) {
					AppealStudent app=(AppealStudent)appObj;
			%>
			<tr style="text-align: center;">
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
				<td style="padding: 0px 20px 0px 20px;"><a class="img-active icon icon-trash clickable" onclick="removeStudent('<%=app.getId()%>')">r</a></td>
			</tr>
			<%} %>
			<%}else{%>
				No Regular students
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
			<tr style="text-align: center;">
				<td><div class="square-small <%=policy%>">
						<div>
						<div style="display: none;" class="titlemok" id="titleN<%=app.getStudent().getSerialNumber()%>">
							<table class="tablemok">
							<tr><th>Lo studente non ha sostenuto i seguenti insegnamenti richiesti:</th></tr>
							<%for(RequestedCourse r:requestedCourses){ %>
							<tr><td>
							<%=r.getCourse().getName() %>
							</td></tr>
							<%} %>
							</table>
						</div>
						</div>
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
				<td style="padding: 0px 20px 0px 20px;"><a class="img-active icon icon-trash clickable" onclick="removeStudent('<%=app.getId()%>')">r</a></td>
			</tr>
			<%}
					} else{%>
					No irregular student
					<%} %>
					<tr><td><div class="line-top"></div></td></tr>
					<tr><td><div class="bottonmok" onclick="openAddStudentToAppeal(<%=appeal.getId()%>)">
					Aggiungi studente					
					</div></td></tr>
		</table>

	</fieldset>






<!-- <td style="padding: 0px 20px 0px 20px;"><a class="icon img-active icon-disk clickable">a</a></td> -->
<!-- 				<td style="padding: 0px 20px 0px 20px;"><a class="icon img-unactive icon-trash clickable">a</a></td> -->









</div>