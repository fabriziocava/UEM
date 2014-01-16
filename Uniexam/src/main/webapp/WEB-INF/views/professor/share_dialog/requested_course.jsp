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
		var ok="si";
		dialogModifyRequestedCourse();
	});
</script>
<%
	Course c = (Course) request.getAttribute("course");
%>
<div id="dialog_content">
	THIS IS DIALOG CONTENT
	<h1 align="center"><%=c.getName()%></h1>
	
	<div>
		<table>
			<%
				for(RequestedCourse req:c.getRequestedCourses()){
			%>
		<tr>
		<td>
		<div>
		<div class="square-small <%=req.getPolicyOfRequest()%>"></div>
		<%=req.getCourse().getName()%>
		<ul class="links-user">
		<li>Opzioni :</li> 
		<li class="bottonmok">Elimina</li>
		<li class="bottonmok" id="modifyRequest<%=req.getCourse().getId()%>$<%=req.getPolicyOfRequest()%>">Modifica</li>
		</ul>
		</div>
		</td>
		</tr>
			<%}
				
			%>
			<%
				
			%>
		</table>
	</div>


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










