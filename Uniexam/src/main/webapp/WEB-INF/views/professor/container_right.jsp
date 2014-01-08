<%@page import="it.unical.uniexam.hibernate.domain.utility.CommentOfPost"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.PostOfGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor"%>
<%@page import="it.unical.uniexam.mvc.service.UtilsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-right">
	<spring:message code="message.title.view.gruops" />
	<br />
	<%
				ArrayList<ArrayList<Object>> struct = (ArrayList<ArrayList<Object>>) request.getAttribute("structNotification");
					if (struct !=null && struct.size() > 0) {
						while (struct.size() > 0) {
							ArrayList<Object>step=struct.remove(0);
				PostOfGroup mes=(PostOfGroup)step.remove(0);
			%>
	<div>
		<%Object []s=new Object[3];
			s[0]=mes.getPost();
			s[1]=mes.getGroup().getName();
			s[2]=step.size();%>
		<spring:message code="message.descr.notify.comment" arguments="<%=s%>" />
	</div>
	<ul>
		<%
			for(Object oj : step){
				CommentOfPost c=(CommentOfPost)oj;
			%>
		<li><%=c.getComment()+" "+c.getDate_of_comment() %></li>
		<%} %>
	</ul>
	<%
					}
				} else {
			%>
	<spring:message code="message.noNews" />
	<%
				}
			%>
</div>