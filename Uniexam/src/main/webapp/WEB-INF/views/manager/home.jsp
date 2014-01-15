<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><spring:message code="label.welcome" /> ${M.getName()}</title>

<link href="${pageContext.request.contextPath}/res/css/manager.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/res/js/jquery-2.0.3.js">
</script>
<script src="${pageContext.request.contextPath}/res/js/mok.js">
</script>

<script src="${pageContext.request.contextPath}/res/js/pino.js">
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
	
	</script>
	<header>
		
		<div class="header">
			<div class="header-logo">
				<a href="${pageContext.request.contextPath}/manager/home"
					title="home"> <img
					src="${pageContext.request.contextPath}/res/img/pixel.png"
					width="30" height="30">
				</a>
			</div>
			<span style="float: left; position: relative; margin-left: 30px;"> <a
				href="?lang=en">en</a> | <a href="?lang=it">it</a>
			</span>
			<div>
			
				<ul class="links-user">
					<li><img src="imageUser" />Sig : ${M.getName()}</li>
					<li><a
						href="${pageContext.request.contextPath}/manager/personalizzation">
							<img class="personalizzation-header"
							src="${pageContext.request.contextPath}/res/img/pixel.png" />
					</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"> <img
							class="logout-header"
							src="${pageContext.request.contextPath}/res/img/pixel.png" />
					</a></li>
				    <li><table class="date"><tr><td><script type="text/javascript">WebDate(); </script></table></li>
				</ul>
				
			</div>
		</div>
	</header>
	<div class="container">

		<div class="container-up">
			<ul  id="jsddm" class="dashboard">
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)">Gestione ordinamenti</a></li>
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)">Gestione sessione esame</a></li>
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)">Gestione corsi</a>
				</li>
								
				<li><a class="button" href="#"
					onclick="selectDashBoard(this.innerHTML)">Assegna corso</a></li>
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