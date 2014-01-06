<%@page import="it.unical.uniexam.hibernate.domain.utility.CommentOfMessage"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.MessageOfGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor"%>
<%@page import="it.unical.uniexam.mvc.service.UtilsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.welcome" /> ${I.getName()}</title>

<link href="${pageContext.request.contextPath}/res/css/professor.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/res/js/jquery-2.0.3.js">
	
</script>
<script src="${pageContext.request.contextPath}/res/js/mok.js">
	
</script>

</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			// 			alert("path: ");
			alineamentoContainer();
			alingDashBoard();
		});
		$(window).bind("resize", resizeWindow);
		function resizeWindow(e) {
			alineamentoContainer();
		}
	</script>
	<script type="text/javascript">
		
	</script>
	<header>
		<div class="header">
			<div class="header-logo">
				<a href="${pageContext.request.contextPath}/professor/home"
					title="home"> <img
					src="${pageContext.request.contextPath}/res/img/pixel.png"
					width="30" height="30">
				</a>
			</div>
			<span style="float: left; position: relative; margin-left: 30px;">
				<a href="?lang=en">en</a> | <a href="?lang=it">it</a>
			</span>
			<div>
				<ul class="links-user">
					<li><img src="imageUser" />${I.getName()}</li>
					<li><a
						href="${pageContext.request.contextPath}/professor/personalizzation">
							<img class="personalizzation-header"
							src="${pageContext.request.contextPath}/res/img/pixel.png" />
					</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"> <img
							class="logout-header"
							src="${pageContext.request.contextPath}/res/img/pixel.png" />
					</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div class="container">

		<div class="container-up">
			<ul class="dashboard">
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)"><spring:message
							code="label.course" /></a></li>
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)"><spring:message
							code="label.appeal" /></a></li>
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)"><spring:message
							code="label.groups" /></a></li>
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)"><spring:message
							code="label.sign" /></a></li>
			</ul>
		</div>
		<div class="container-left">something asd asd asd asd asd asd
			asd asd asd asd asd asd asds asd asd asd asd asd asd asds asd asd asd
			asd asds asd asd asd asd asd asd asds asd asd asd asd asd asd asds
			asd asd asd asds asd asd asd asd asd asd asds a dasd</div>
		<div class="container-center">also here</div>
		<div class="container-right">
			<spring:message code="message.title.view.gruops" />
			<br />
			<%
				ArrayList<ArrayList<Object>> struct = (ArrayList<ArrayList<Object>>) request
						.getAttribute("structNotification");
				if (struct.size() > 0) {
					while (struct.size() > 0) {
						ArrayList<Object>step=struct.remove(0);
			MessageOfGroup mes=(MessageOfGroup)step.remove(0);
			%>
			<div>
			<%Object []s=new Object[3];
			s[0]=mes.getMessage();
			s[1]=mes.getGroup().getName();
			s[2]=step.size();%>
			<spring:message code="message.descr.notify.comment" arguments="<%=s%>"/>
			</div>
			<ul>
			<%
			for(Object oj : step){
				CommentOfMessage c=(CommentOfMessage)oj;
			%>
				<li>
				<%=c %>
				</li>
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
		<div class="container-down">basta</div>
	</div>
	<div>
		<footer>Foooooooooooooooooooot</footer>
	</div>
</body>
</html>
