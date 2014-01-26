<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->

<%
	Appeal appeal=(Appeal) request.getAttribute("appeal");
		if(appeal!=null){
%>

<script type="text/javascript">
$(document).ready(function(){
	$("#tableSortable").tablesorter();
	$('.titlemok').each(function(item,element){
		titlemok('square-small', element.id);
	});
	listRemove=new FormData();
	count=1;
	listRemove.append("idAppeal",'<%=appeal.getId()%>');
	
	listPrepare=new FormData();
	count=1;
	listPrepare.append("idAppeal",'<%=appeal.getId()%>');
});
function dialogRemoveStudent(id){
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
					listRemove.append((count++)+"idAppealStudent",id);
					actionAppealStudents(listRemove,'sign/remove_students');
					count=0;
					$(this).dialog("close");
					$("div").remove("#flashDialog");
				},
				"Cancel" : function() {
					$(this).dialog("close");
					$("div").remove("#flashDialog");
				}
			}
		});
	}
	function actionAppealStudents(listRemove,path){
		var conte=$("#context").attr("value");
			var ing = $.ajax({
				url : conte + '/ajax/'+path,
				type : "POST",
				data : listRemove,
				processData : false,
				contentType : false
			});
			ing.done(function(data) {
				if(data.match("ok")){
					location.reload();
					alert("Modifiche apportate!");
				}else{
					alert("Errore nell'apportate le modifiche!");
				}
			});
	}
	function removeNoSelected(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked)
				;
			else {
				$(this).parent().parent().remove();
			}
		});
	}
	function removeSelected(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked)
				$(this).parent().parent().remove();
		});
	}
	function deleteNoSelected(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked);
			else {
				$(this).parent().parent().remove();
				listRemove.append((count++)+'idAppealStudent',this.value);
				
			}
		});
		actionAppealStudents(listRemove,'sign/remove_students');
		count=0;
	}
	function deleteSelected(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked){
				$(this).parent().parent().remove();
				listRemove.append((count++)+'idAppealStudent',this.value);
			}
		});
		actionAppealStudents(listRemove,'sign/remove_students');
		count=0;
	}
	function applyPrepare(name){
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked){
				$(this).parent().parent().remove();
				listPrepare.append((count++)+'idAppealStudent',this.value);
			}
		});
		actionAppealStudents(listPrepare,'sign/prepare_students');
		count=0;
	}
</script>

<%
	
%>
<div class="container-center">

	<div class="aligncenter">
		<h2 style="text-align: center;"><%=appeal.getName()%></h2>
	</div>
	<div class="aligncenter">
		<%
			ArrayList<ArrayList<Object>> appealStudentsRegAndNot = (ArrayList<ArrayList<Object>>) request
						.getAttribute("appealStudents");
				ArrayList<Object> appealStudents = appealStudentsRegAndNot
						.get(0);
				ArrayList<Object> appealStudentsNoRegular = appealStudentsRegAndNot
						.get(1);
		%>
		<fieldset>
			<legend>
				<spring:message
					code="message.professor.course.container.center.legendCourses" />
			</legend>
			<div id="dragButton1"  class="box">
				<div>
					<span style="margin-top: 10px;margin-left: -20px" 
					class="lock-draggable-close" id="draggabledragButton1">lock/unlock</span>
					<fieldset class="aligncenter linkNoMNoP trasparent030">
						<legend>Local modification</legend>
						<ul style="padding: 10px" class="linkNoMNoP">
							<li class="aligncenter" style="float: left; margin-right: 60px;"><buttonmok
									onclick="removeNoSelected('selctedStudent')">Remove
								Unselected</buttonmok></li>
							<li class="alignend"><buttonmok
									onclick="removeSelected('selctedStudent')">Remove
								Selected</buttonmok></li>
						</ul>
						<br>
					</fieldset>
				</div>
			</div>
			<div id="dragButton2"  class="box">
				<div>
					<span style="margin-top: 10px;margin-left: -20px"
					 class="lock-draggable-close" id="draggabledragButton2">lock/unlock</span>
					<fieldset class="aligncenter linkNoMNoP trasparent030">
						<legend>Persistent modification</legend>
						<ul style="padding: 10px" class="linkNoMNoP">
							<li class="aligncenter" style="float: left; margin-right: 60px;"><buttonmok
									onclick="deleteNoSelected('selctedStudent')">Delete
								Unselected</buttonmok></li>
							<li class="alignend"><buttonmok
									onclick="deleteSelected('selctedStudent')">Delete
								Selected</buttonmok></li>
						</ul>
						<br>
					</fieldset>
				</div>
			</div>
			<buttonmok onclick="applyPrepare('selctedStudent')"
				style="margin-top:20px">Apply Prepare</buttonmok>
			<br>
			<table class="tablemok" id="tableSortable">
				<thead>
					<tr style="text-align: center;">
						<th style="padding: 0px 20px 0px 20px;">Stato</th>
						<th style="padding: 0px 20px 0px 20px;">Matricola</th>
						<th style="padding: 0px 20px 0px 20px;">Nome</th>
						<th style="padding: 0px 20px 0px 20px;">Voto</th>
						<th style="padding: 0px 20px 0px 20px;">Nota</th>
						<th style="padding: 0px 20px 0px 20px;">Elimina</th>
						<th style="padding: 0px 20px 0px 20px;">Seleziona</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (appealStudents != null && appealStudents.size() > 0) {
								for (Object appObj : appealStudents) {
									AppealStudent app = (AppealStudent) appObj;
					%>
					<tr class="line-top" style="text-align: center;">
						<td></td>
						<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getSerialNumber()%></td>
						<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getName()%>
							<%=app.getStudent().getSurname()%></td>
						<td style="padding: 0px 20px 0px 20px;" contenteditable="true"
							onfocus="storeOld(this)"
							onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','temporany_vote','D')">
							<%=app.getTemporany_vote()%></td>
						<td style="padding: 0px 20px 0px 20px;" contenteditable="true"
							onfocus="storeOld(this)"
							onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','note','S')">
							<%=app.getNote()%></td>
						<td style="padding: 0px 20px 0px 20px;"><a
							class="img-active icon icon-trash clickable"
							onclick="dialogRemoveStudent('<%=app.getId()%>')">re</a></td>
						<td style="padding: 0px 20px 0px 20px;"><input
							type="checkbox" name="selctedStudent" value="<%=app.getId()%>" /></td>
					</tr>
					<%
						}
					%>
					<%
						} else {
					%>
					<!-- 				No Regular students -->
					<%
						}
					%>
					<%
						if (appealStudentsNoRegular != null
									&& appealStudentsNoRegular.size() > 0) {
								// 					ArrayList<ArrayList<RequestedCourse>>requestedCourses=(ArrayList<ArrayList<RequestedCourse>>)request.getAttribute("requestedCourses");
								for (Object appAO : appealStudentsNoRegular) {

									ArrayList<Object> appO = (ArrayList<Object>) appAO;

									AppealStudent app = (AppealStudent) appO.get(0);
									ArrayList<RequestedCourse> requestedCourses = (ArrayList<RequestedCourse>) appO
											.get(1);

									String policy = RequestedCourse.POLICY_LIGHT;
									for (RequestedCourse req : requestedCourses) {
										if (req.getPolicyOfRequested().equals(
												RequestedCourse.POLICY_MEDIUM)) {
											policy = RequestedCourse.POLICY_MEDIUM;
										}
										if (req.getPolicyOfRequested().equals(
												RequestedCourse.POLICY_STRONG)) {
											policy = RequestedCourse.POLICY_STRONG;
											break;
										}
									}
					%>
					<tr class="line-top" style="text-align: center;">
						<td style="display: inline-block">
							<div class="square-small <%=policy%>"
								onclick="openDiv('titleN<%=app.getStudent().getSerialNumber()%>')"></div>
							<div style="display: none;" class="titlemok"
								id="titleN<%=app.getStudent().getSerialNumber()%>">
								<buttonmok onclick="closeDiv(this)" class="closeButton">X</buttonmok>
								<table class="tablemok">
									<tr>
										<th>Lo studente non ha sostenuto i seguenti insegnamenti
											richiesti:</th>
									</tr>
									<%
										for (RequestedCourse r : requestedCourses) {
									%>
									<tr>
										<td><%=r.getCourse().getName()%></td>
									</tr>
									<%
										}
									%>
								</table>
							</div>
						</td>
						<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getSerialNumber()%></td>
						<td style="padding: 0px 20px 0px 20px;"><%=app.getStudent().getName()%>
							<%=app.getStudent().getSurname()%></td>
						<td style="padding: 0px 20px 0px 20px;" contenteditable="true"
							onfocus="storeOld(this)"
							onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','temporany_vote','D')">
							<%=app.getTemporany_vote()%></td>
						<td style="padding: 0px 20px 0px 20px;" contenteditable="true"
							onfocus="storeOld(this)"
							onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal_student','<%=app.getId()%>','note','S')">
							<%=app.getNote()%></td>
						<td style="padding: 0px 20px 0px 20px;"><a
							class="img-active icon icon-trash clickable"
							onclick="dialogRemoveStudent('<%=app.getId()%>')">re</a></td>
						<td style="padding: 0px 20px 0px 20px;"><input
							type="checkbox" name="selctedStudent" value="<%=app.getId()%>"/></td>
					</tr>
					<%
						}
							} else {
					%>
					<!-- 					No irregular student -->
					<%
						}
					%>
				</tbody>
			</table>
		</fieldset>
	</div>
</div>
<%
	} else {
%>
Errore!
<%}%>