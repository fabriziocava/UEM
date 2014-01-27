<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
<%

		ArrayList<Student> students=(ArrayList<Student>)request.getAttribute("list");
	%>

<%for(Student s:students){ %>
<!-- <tr><td> -->
<!-- <table class="line-top table-list"> -->
<tr onmouseout="deselemok(this)" onmouseover="selemok(this)" onclick="selectmok(this,'idComplete','studentID','risTable')" style="text-align: left;" id="<%=s.getId() %>">
	<td style="padding: 0px 20px 0px 0px;"><%=s.getSerialNumber() %></td>
	<td style="padding: 0px 20px 0px 0px;"><%=s.getName() %></td>
	<td style="padding: 0px 20px 0px 0px;"><%=s.getSurname() %></td>
</tr>
<!-- </table> -->
<!-- </td></tr> -->
<%} %>