<%@page import="it.unical.uniexam.hibernate.domain.utility.PhoneNumber"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.Email"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.PhoneNumber.TYPE"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
</script>


<div id="dialog_content">
<%
ArrayList<Professor>coms=(ArrayList<Professor>)request.getAttribute("commissionary");
if(coms!=null && coms.size()>0){
%>
<div class="aligncenter">
<table class="tablemok morespace">
<tr>
<th>Nome</th><th>web site</th><th>phone</th><th>email</th>
</tr>
<%for(Professor c:coms){ %>
<%
Email e=c.getEmail(it.unical.uniexam.hibernate.domain.utility.Email.TYPE.UNUFFICIAL);
String email="No email";
if(e!=null)
	email=e.getEmail();
PhoneNumber p=c.getPhoneNumber(TYPE.UFFICIAL);
String number="No number";
if(p!=null)
	number=p.getNumber();
%>
<tr>
<td><%=c.getName() %> , <%=c.getSurname() %></td>
<td><%=c.getWebSite() %></td>
<td><%=number %></td>
<td><%=email %></td>
<% %>
<% %>
</tr>
<%} %>
</table>
</div>
<%}else{%>
<div class="aligncenter">
No Commissary
</div>
<% }%>
<div class="aligncenter">
<buttonmok>Aggiungi commissario</buttonmok>
</div>
<%%>
</div>