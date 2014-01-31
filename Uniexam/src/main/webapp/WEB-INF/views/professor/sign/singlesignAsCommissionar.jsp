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

%>

<script type="text/javascript">
$(document).ready(function(){
	$("#tableSortable").tablesorter();
	$('.titlemok').each(function(item,element){
		titlemok('square-small', element.id);
	});
	listRemove=new FormData();
	count=1;
	
	listSign=new FormData();
	count=1;
});
function dialogRemoveStudent(id){
	$('<div></div>')
	.attr('id','flashDialog')
	.attr('title',"<spring:message code='message.professor.remove.student'/>")
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
			"<spring:message code='message.general.confirm'/>":function(){
					listRemove.append((count++)+"idAppealStudent",id);
					actionAppealStudents(listRemove,'sign/remove_students');
					count=0;
					$(this).dialog("close");
					$("div").remove("#flashDialog");
				},
				"<spring:message code='message.general.cancel'/>" : function() {
					$(this).dialog("close");
					$("div").remove("#flashDialog");
				}
			}
		});
	}
	function declassifyNoSelect(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked);
			else {
				$(this).parent().parent().remove();
				listRemove.append((count++)+'idAppealStudent',this.value);
				
			}
		});
		actionAppealStudents(listRemove,'sign/declassify_students');
		count=0;
	}
	function declassifySelected(name) {
		$("input[name='" + name + "']:checkbox").each(function() {
			if (this.checked){
				$(this).parent().parent().remove();
				listRemove.append((count++)+'idAppealStudent',this.value);
			}
		});
		actionAppealStudents(listRemove,'sign/declassify_students');
		count=0;
	}
</script>

<%
	
%>
<div class="container-center">

	<div class="aligncenter">
		<h2 style="text-align: center;">Titolo</h2>
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
						<legend><spring:message code='message.professor.localmodification'/></legend>
						<ul style="padding: 10px" class="linkNoMNoP">
							<li class="aligncenter" style="float: left; margin-right: 60px;"><buttonmok
								onclick="removeNoSelected('selctedStudent')"><spring:message code='message.professor.removeunselected'/></buttonmok></li>
							<li class="alignend"><buttonmok
									onclick="removeSelected('selctedStudent')"><spring:message code='message.professor.removeselected'/></buttonmok></li>
								<li class="alignend" style="float: left;margin-top: 10px;margin-right: 60px;">
								<buttonmok
									onclick="selectAll('selctedStudent')"><spring:message code='message.professor.selectall'/></buttonmok></li>
								<li class="alignend" style="margin-top: 10px;"><buttonmok
									onclick="deselectAll('selctedStudent')"><spring:message code='message.professor.deselectall'/></buttonmok></li>
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
						<legend><spring:message code='message.professor.persistentmodification'/></legend>
						<ul style="padding: 10px" class="linkNoMNoP">
							<li class="aligncenter" style="float: left; margin-right: 60px;"><buttonmok
									onclick="declassifyNoSelect('selctedStudent')"><spring:message code='message.professor.declassifyunselect'/></buttonmok></li>
							<li class="alignend"><buttonmok
									onclick="declassifySelected('selctedStudent')"><spring:message code='message.professor.declassifyselect'/></buttonmok></li>
						</ul>
						<br>
					</fieldset>
				</div>
			</div>
			<buttonmok onclick="applySign('selctedStudent','_by_commissary')"
				style="margin-top:20px"><spring:message code='message.professor.applysign'/></buttonmok>
			<br>
			<input type="text" 
			onkeyup="filterMok('#tableSortable tbody tr.table-item-space',this.value)"
			placeholder="search"/>
			<table class="tablemok" id="tableSortable">
				<thead>
					<tr style="text-align: center;" class="table-item-space">
						<th ><spring:message code='message.professor.general.state'/></th>
						<th ><spring:message code='message.professor.general.serialnumber'/></th>
						<th ><spring:message code='message.professor.general.name'/></th>
						<th ><spring:message code='message.professor.general.vote'/></th>
						<th ><spring:message code='message.professor.general.note'/></th>
						<th ><spring:message code='message.general.delete'/></th>
						<th ><spring:message code='message.professor.general.select'/></th>
					</tr>
				</thead>
				<tbody>
					<%
						if (appealStudents != null && appealStudents.size() > 0) {
								for (Object appObj : appealStudents) {
									AppealStudent app = (AppealStudent) appObj;
					%>
					<tr class="line-top table-item-space" style="text-align: center;">
						<td></td>
						<td ><%=app.getStudent().getSerialNumber()%></td>
						<td ><%=app.getStudent().getName()%>
							<%=app.getStudent().getSurname()%></td>
						<td  >
							<%=app.getTemporany_vote()%></td>
						<td  >
							<%=app.getNote()%></td>
						<td ><a
							class="img-active icon icon-trash clickable"
							onclick="dialogRemoveStudent('<%=app.getId()%>')">re</a></td>
						<td ><input
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
					<tr class="line-top table-item-space" style="text-align: center;">
						<td style="display: inline-block">
							<div class="square-small <%=policy%>"
								onclick="openDiv('titleN<%=app.getStudent().getSerialNumber()%>')"></div>
							<div class="titlemok startHide"
								id="titleN<%=app.getStudent().getSerialNumber()%>">
								<buttonmok onclick="closeDiv(this)" class="closeButton">X</buttonmok>
								<table class="tablemok">
									<tr>
										<th><spring:message code='message.professor.requestedcoursealert'/></th>
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
						<td ><%=app.getStudent().getSerialNumber()%></td>
						<td ><%=app.getStudent().getName()%>
							<%=app.getStudent().getSurname()%></td>
						<td  >
							<%=app.getTemporany_vote()%></td>
						<td  >
							<%=app.getNote()%></td>
						<td ><a
							class="img-active icon icon-trash clickable"
							onclick="dialogRemoveStudent('<%=app.getId()%>')">re</a></td>
						<td ><input
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
<div id="passwdDIV" title="Inserisci password" class="startHide">
<input id="passwd" placeholder="password" type="password">
</div>
