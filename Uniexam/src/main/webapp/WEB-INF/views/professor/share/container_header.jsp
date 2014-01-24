<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<div >
		<a href="${pageContext.request.contextPath}/professor/home" class="header-logo"
			title="home"> 
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
			<li><a href="${pageContext.request.contextPath}/professor/account" title="Gestione Account">
				<img class="image30" src="${pageContext.request.contextPath}/professor/image"/>${I.getName()}
			</a></li>
			<li><a class="logout-header" href="${pageContext.request.contextPath}/logout"> 
			</a></li>
		</ul>
<!-- 		<ul class="links-user"> -->
<%-- 			<li><img src="imageUser" />${I.getName()}</li> --%>
<!-- 			<li><a class="personalizzation-header" -->
<%-- 				href="${pageContext.request.contextPath}/professor/personalizzation"> --%>
<!-- 			</a></li> -->
<%-- 			<li><a class="logout-header" href="${pageContext.request.contextPath}/logout">  --%>
<!-- 			</a></li> -->
<!-- 		</ul> -->
	</div>
</div>
