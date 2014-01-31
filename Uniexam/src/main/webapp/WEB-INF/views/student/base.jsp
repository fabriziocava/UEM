<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.welcome" /> ${I.getName()}</title>

<link href="${pageContext.request.contextPath}/res/css/student.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/res/js/jquery-2.0.3.js">
</script>
<script
	src="${pageContext.request.contextPath}/res/js/jquery-ui-1.10.3.custom.js">
</script>
<script
	src="${pageContext.request.contextPath}/res/js/jquery-sorting.js">
</script>
<script
	src="${pageContext.request.contextPath}/res/js/jquery-ui-timepicker-addon.js">
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/js/jquery-ui-timepicker-addon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/jquery-ui.css">
<script src="${pageContext.request.contextPath}/res/js/fabrizio.js">
</script>

<script>
  $(function() {
    $( document ).tooltip({
    	show: {
            effect: "slideDown",
            delay: 150
          },
    track:true
    });
  });
 </script>

</head>
<body>
	<div class="processing">
		<img /> Loading ...
	</div>
<header>
	<tiles:insertAttribute name="container-header" />
</header>
	<input type="hidden" id="context"
		value="${pageContext.request.contextPath}/student" />
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}" />
<div class="container">
	<tiles:insertAttribute name="container-up" />
	<tiles:insertAttribute name="container-left" />
	<tiles:insertAttribute name="container-center" />
	<tiles:insertAttribute name="container-right" />
	<tiles:insertAttribute name="container-down" />
</div>
<div class="container-footer">
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</div>
</body>
</html>
