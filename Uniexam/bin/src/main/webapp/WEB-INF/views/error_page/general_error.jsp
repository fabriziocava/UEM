<%@page import="it.unical.uniexam.mvc.service.UtilsService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${error_type} Error</title>
</head>
<body>
	<h1>${error_type}
		:
		${error_message}</h1>
</body>
</html>