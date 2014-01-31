<%@page import="it.unical.uniexam.hibernate.domain.utility.Email.TYPE"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
<%

		ArrayList<Professor> obj=(ArrayList<Professor>)request.getAttribute("list");
	%>

<%for(Professor s:obj){ %>
<tr onmouseout="deselemok(this)" onmouseover="selemok(this)" onclick="selectmok(this,'idCompleteProfessor','professorID','risTable')" style="text-align: left;" id="<%=s.getId() %>">
	<td style="padding: 0px 20px 0px 0px;"><%=s.getName() %></td>
	<td style="padding: 0px 20px 0px 0px;"><%=s.getSurname() %></td>
	<%if(s.getEmail(TYPE.UFFICIAL)!=null){ %>
	<td style="padding: 0px 20px 0px 0px;"><%=s.getEmail(TYPE.UFFICIAL).getEmail() %></td>
	<%}else{ %>
	<td style="padding: 0px 20px 0px 0px;">No Email</td>
	<%} %>
</tr>
<%} %>