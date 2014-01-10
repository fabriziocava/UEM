<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
<script src="${pageContext.request.contextPath}/res/js/jquery-ui-1.10.3.custom.js">
</script>
<script src="${pageContext.request.contextPath}/res/js/mok.js">
	
</script>
<script type="text/javascript">
		
	</script>
</head>
<body>
	<header>
		<tiles:insertAttribute name="container-header" />
	</header>
	<input type="hidden" id="context" value="${pageContext.request.contextPath}/professor" />
		<tiles:insertAttribute name="draggable-groups-box" />
		<tiles:insertAttribute name="draggable-notify-box" />
	<div class="container">
		<tiles:insertAttribute name="container-up" />
		<tiles:insertAttribute name="container-left" />
		<tiles:insertAttribute name="container-center" />
		<tiles:insertAttribute name="container-down" />
	</div>
	<div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>
