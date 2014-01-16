<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user} <spring:message code="label.welcome" /></title>

<link href="${pageContext.request.contextPath}/res/css/secretary.css"
	media="all" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/res/css/menu.css"
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
		function resizeWindow( e ) {
			alineamentoContainer();
		}

	</script>
	<script type="text/javascript">
		$(document).ready(function () {
		      $('#nav li').hover(
		        function () {
		            //mostra sottomenu
		            $('ul', this).stop(true, true).delay(50).slideDown(100);
		 
		        }, 
		        function () {
		            //nascondi sottomenu
		            $('ul', this).stop(true, true).slideUp(200);        
		        }
		    );
		});
	</script>
	<header>
		<div class="header">
			<div class="header-logo">
				<a href="${pageContext.request.contextPath}/secretary/home"
					title="home"> <img
					src="${pageContext.request.contextPath}/res/img/pixel.png"
					width="30" height="30">
				</a>
			</div>
	        <span style="float: left; position: relative; margin-left: 30px;">
	                <a href="?lang=en">
	                <img class="image20" src="${pageContext.request.contextPath}/res/img/eng_flag.png"/></a> 
	                | 
	                <a href="?lang=it">
	                <img class="image20" src="${pageContext.request.contextPath}/res/img/it_flag.png"/></a>
	        </span>
			<div>
				<ul class="links-user">
					<li><img src="imageUser" />${user}</li>
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
			<ul id="nav">
		    <li class="first"><a href="#"><spring:message code="label.insert" /></a>
		    	<ul>
			    	<li><a href="${pageContext.request.contextPath}/secretary/registerDepartment"><spring:message code="label.department" /></a></li>
		    		<li><a href="#"><spring:message code="label.degreeCourse" /></a></li>
		    		<li><a href="${pageContext.request.contextPath}/secretary/registerManager"><spring:message code="label.manager" /></a></li>
		    		<li><a href="#"><spring:message code="label.professor" /></a></li>
		    		<li><a href="#"><spring:message code="label.student" /></a></li>
		    	</ul>
		    </li>
		    <li><a href="#">Voce 02</a>
		        <ul>
		            <li><a href="#">Sottovoce Menu Numero 01</a></li>
		            <li><a href="#">Sottovoce Menu Numero 02</a></li>
		            <li class="last"><a href="#">Sottovoce Menu Numero 03</a></li>
		        </ul>
		        <div class="clear"></div>
		    </li>
		    <li><a href="#">Voce 03</a>
		    <ul>
		        <li><a href="#">Sottovoce Menu Numero 04</a></li>
		        <li><a href="#">Sottovoce Menu Numero 05</a></li>
		        <li><a href="#">Sottovoce Menu Numero 06</a></li>
		        <li class="last"><a href="#">Sottovoce Menu Numero 07</a></li>
		    </ul>         
		        <div class="clear"></div>
		    </li>
		    <li class="last"><a href="#">Voce 04</a></li>
		</ul>
		</div>
		
		
		<div class="container-left">something asd asd asd asd asd asd
			asd asds asd asd asd asd asd asd asds asd asd asd asd asd asd asds
			asd asd asd asd asd asd asds asd asd asd asd asd asd asds asd asd asd
			asd asd asd asds asd asd asd asd asd asd asds a dasd</div>
		<div class="container-center">also here</div>
		<div class="container-right">also here too</div>
		<div class="container-down">basta</div>
	</div>
	<div>
		<footer>Foooooooooooooooooooot</footer>
	</div>
</body>
</html>