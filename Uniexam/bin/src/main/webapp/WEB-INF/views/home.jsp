<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@ page session="false" %>
<!DOCTYPE html>

<html>
<head>
<title>Benvenuto in Uniexam</title>
<link href="res/css/login.css" media="all" rel="stylesheet" type="text/css">
</head>

<body>

<form id="login" action="./login" method="post">
    <h1>Uniexam</h1>
    <fieldset id="inputs">
        <input id="username" name="username" type="text" placeholder="Username" autofocus required>   
        <input id="password" name="password" type="password" placeholder="Password" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="Log in">
        <a href="">Forgot your password?</a>
    </fieldset>
</form>

</body>
</html>