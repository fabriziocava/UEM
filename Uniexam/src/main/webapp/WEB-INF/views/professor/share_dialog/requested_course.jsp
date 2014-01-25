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
function addRequestedCourseF(id){
	var conte=$("#context").attr("value");
	var idCourse=id;
	var ajax=sendAJAXmessage(conte+"/ajax/dialog/addRequestedCourse", "GET", "id", idCourse);
	ajax.done(function(data){
		if($("#dialogAddRequested").html()==undefined)
			$("<div></div>").attr('id','dialogAddRequested').appendTo('body');
		$("#dialogAddRequested").html(data);
	});
}

function submitCommandRequestedCourse(){
	var data=commands.toString();
	commands=undefined;
	var conte=$("#context").attr("value");
	var ajax=sendAJAXmessage(conte+"/ajax/dialog/requested_course/command", "POST", "data", data);
	ajax.done(function(data){
		if($("#dialog").html()==undefined)
			$("<div></div>").attr('id','dialog').appendTo('body');
		$("#dialog").html(data);
	});
//	alert(data);
}

function modifyRequestedCourse(id,degree,idCourse){
	try{
		if(commands==undefined)
			commands=new Commands("sendRequestedCourse",idCourse);
	}catch(ERR){
		commands=new Commands("sendRequestedCourse",idCourse);
	}
	$("<div></div>")
	.attr('id','divRequestCourseChange')
	.appendTo('body').html($("#radio"+degree).html());
	$("#divRequestCourseChange").attr("title","Modify Requested Course");
	var dial=$("#divRequestCourseChange");
	dial.dialog({
		resizable: false,
		modal: true,
		buttons: {
			"Save": function() {
				var newVal=$("input[name='choose']:radio:checked").val();
				commands.add(id, "change", newVal);
				$(".alertSomeModifyRequestCourse").each(function(){
					$(this).slideDown(); 
				});
				dirtingTheElement();
//				requested_courseAddIfNotAddAlready ///strutturaaaa!!! classeEEEEE
//				var comm="%requested"+id+"$change"+newVal+"%";
				dial.dialog( "close" );
				$("div").remove("#divRequestCourseChange");
			},
			Cancel: function() {
				dial.dialog( "close" );
				if($("#sendRequestCourseChange").val()==""){
					$("input").remove("#sendRequestCourseChange");
				}
				$("div").remove("#divRequestCourseChange");
			}
		},
		close:function(){
			dial.dialog( "close" );
			$("div").remove("#divRequestCourseChange");
		}
	});
	$("#divRequestCourseChange").attr("title","");
	$("#setRequestCourseChange").css('height',"auto");
}

function deleteRequestedCourse(id,idCourse){
	try{
		if(commands==undefined)
			commands=new Commands("sendRequestedCourse",idCourse);
	}catch(ERR){
		commands=new Commands("sendRequestedCourse",idCourse);
	}
	commands.add(id, "remove", "no");
	$(".alertSomeModifyRequestCourse").each(function(){
		$(this).slideDown(); 
	});
	dirtingTheElement();
}

</script>
<%
	Course c = (Course) request.getAttribute("course");
%>
<div id="dialog_content">
	
	<h1 align="center"><%=c.getName()%></h1>
	<p class="alertSomeModifyRequestCourse" style="display: none;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
	<spring:message code="message.professor.dialog.requested_course.alert_Save_Modification" /></p>
	<div>
		<table class="tablemok">
			<%
				for(RequestedCourse req:c.getRequestedCourses()){
			%>
		<tr>
		<td>
		<div>
		<div class="titlemok" id="legendOfDegree">
		<table class="tablemok">
		<tr>
		<th><div class="square-small strong"></div></th>
		<td><div><spring:message code="message.professor.dialog.requested_course.description_strong" /></div></td>
		</tr>
		<tr>
		<th><div class="square-small medium"></div></th>
		<td><div><spring:message code="message.professor.dialog.requested_course.description_medium" /></div></td>
		</tr>
		<tr>
		<th><div class="square-small light"></div></th>
		<td><div><spring:message code="message.professor.dialog.requested_course.description_light" /></div></td>
		</tr>
		</table>
		</div>
		
		<div class="line-top"></div>
		<div class="square-small <%=req.getPolicyOfRequested()%>"></div>
		<div id="requestedCourse<%=req.getCourse().getId()%>"><%=req.getCourse().getName()%></div>
		<ul class="links-user">
		<li><spring:message code='message.general.options' /> :</li> 
		<li class="bottonmok" id="deleteRequest<%=req.getCourse().getId()%>$<%=c.getId()%>"><spring:message code='message.general.remove' /></li>
		<li class="bottonmok" onclick="modifyRequestedCourse('<%=req.getCourse().getId()%>','<%=req.getPolicyOfRequested()%>','<%=c.getId()%>')" ><spring:message code='message.general.modify' /></li>
		</ul>
<%-- 		id="modifyRequest<%=req.getCourse().getId()%>$<%=req.getPolicyOfRequested()%>$<%=c.getId()%>" --%>
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
			<div class="bottonmok" onclick="addRequestedCourseF('<%=c.getId()%>')"><spring:message code='message.general.add' /></div>
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
<div class="alertSomeModifyRequestCourse line-top" style="display: none;"><input type="button" onclick="submitCommandRequestedCourse()" value="<spring:message code='message.general.Save' />"></div>

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










