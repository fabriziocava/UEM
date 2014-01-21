<%@page import="javassist.expr.NewArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.ExamSession"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function(){
		dialogViewExamSession();
		initCollapsable();
	});
</script>

</head>
<body>

<div id="dialog_content">
<%ExamSession examsession=(ExamSession)request.getAttribute("examsession"); %>

<table class="tablemok">


</table>

<table class="tablemok">
<tr>
<th><%=examsession.getDescription() %></th>
<td><div class="line-top"></div></td>
<td><div class="bottonmok color_red" onclick=""><spring:message code="message.general.delete" /></div></td>
<td><div class="bottonmok " onclick=""><spring:message code="message.general.modify" /></div></td>
</tr>
</table>

</div>

</body>
</html>