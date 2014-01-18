<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user} <spring:message code="label.secretary.welcome" /></title>

<link href="${pageContext.request.contextPath}/res/css/secretary.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/res/js/jquery-2.0.3.js">
</script>
<script src="${pageContext.request.contextPath}/res/js/mok.js">
</script>

<script type="text/javascript">
	$(document).ready(function() {
		//alert("path: ");
		alineamentoContainer();
		alingDashBoard();
	});
	$(window).bind("resize", resizeWindow);
	function resizeWindow( e ) {
		alineamentoContainer();
	}
</script>

</head>
<body>

<header>
	<tiles:insertAttribute name="container-header" />
</header>
<div class="container">
	<tiles:insertAttribute name="container-up" />
	<tiles:insertAttribute name="container-left" />
	<tiles:insertAttribute name="container-center" />
	<tiles:insertAttribute name="container-down" />
</div>
<div class="container-footer">
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</div>
</body>
</html>