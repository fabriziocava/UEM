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
	});
// 	function legendOfDegree(){
		
// 	}
// 	$(".square-small")
// 	.mouseover(function(){
		
// 	})
// 	.mouseout(function(){
		
// 	});
	var timer;
	var delay=1000;
	$('.square-small').hover(function(event) {
	    // on mouse in, start a timeout
// 	alert(event.pageX);
	    
	    timer = setTimeout(function() {
	    	$("#legendOfDegree").css("left",event.pageX);
	    	$("#legendOfDegree").css("top",event.pageY);
	        $("#legendOfDegree").fadeIn();
	        $("#legendOfDegree").attr("display","block");
	    }, delay);
	}, function() {
		$("#legendOfDegree").fadeOut();
	    clearTimeout(timer);
	});
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
		<div style="display: none;position: fixed;z-index: 9;background-color: gray;" id="legendOfDegree">
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
		<li class="bottonmok" id="modifyRequest<%=req.getCourse().getId()%>$<%=req.getPolicyOfRequested()%>$<%=c.getId()%>"><spring:message code='message.general.modify' /></li>
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










