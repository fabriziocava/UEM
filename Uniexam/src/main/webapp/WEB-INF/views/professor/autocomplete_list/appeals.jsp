<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->

<%
		ArrayList<Appeal> appeals=(ArrayList<Appeal>)request.getAttribute("listAppeals");
		if(appeals!=null && appeals.size()>0){
	%>

<%for(Appeal app:appeals){ %>
<tr onmouseout="deselemok(this)" onmouseover="selemok(this)" onclick="selectmok(this,'idComplete','appealID','risTable')" style="text-align: left;" id="<%=app.getId() %>">
	<td style="padding: 0px 20px 0px 0px;"><%=app.getName() %></td>
	<td style="padding: 0px 20px 0px 0px;"><%=app.getLocation() %></td>
	<td style="padding: 0px 20px 0px 0px;"><%if(app.getCourse()!=null){%><%=app.getCourse().getName() %><%}else{ %>No Course<%} %></td>
</tr>
<%} 
}else{%>
NO 
<%}%>