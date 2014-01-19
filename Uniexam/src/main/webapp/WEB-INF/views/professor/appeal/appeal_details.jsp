<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
<%

		Appeal appeal=(Appeal)request.getAttribute("appeal");
	%>

<div>
<script type="text/javascript">

</script>
<table>

<tr>
<th>Name</th><td>
<div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','name','String')">
<%=appeal.getName() %></div></td>
</tr>

<tr>
<th>Description</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','description','String')">
<%=appeal.getDescription() %></div></td>
</tr>

<tr>
<th>Location</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','location','String')">
<%=appeal.getLocation() %></div></td>
</tr>

<tr>
<th>Course</th>
<td><%=appeal.getCourse().getName() %></td>
</tr>

<tr>
<th>Max number of inscribes</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','maxNumberOfInscribed','Integer')">
<%=appeal.getMaxNumberOfInscribed() %></div></td>
</tr>

<tr>
<th>Date open appel</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','openDate','Date')">
<%=appeal.getOpenDate() %></div></td>
</tr>

<tr>
<th>Date close appel</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','closeDate','Date')">
<%=appeal.getCloseDate() %></div></td>
</tr>

<tr>
<th>Date exam</th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','examDate','Date')">
<%=appeal.getExamDate() %></div></td>
</tr>

</table>

</div>