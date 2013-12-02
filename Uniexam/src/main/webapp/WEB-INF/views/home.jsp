<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>

<html>
<head>
<title>Benvenuto in Uniexam</title>
<link href="css/primo.css" media="all" rel="stylesheet" type="text/css">
</head>

<body>

<form id="login">
    <h1>Log In</h1>
    <fieldset id="inputs">
        <input id="username" type="text" placeholder="Username" autofocus required>   
        <input id="password" type="password" placeholder="Password" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="Log in">
        <a href="">Forgot your password?</a><a href="">Register</a>
    </fieldset>
</form>
<a href="./student/home">sp</a>

</body>
</html>
