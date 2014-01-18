<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<div >
		<a href="${pageContext.request.contextPath}/student/home" class="header-logo"
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
			<li><img src="imageUser" />${user}</li>
			<li><a href="${pageContext.request.contextPath}/logout"> <img
					class="logout-header"
					src="${pageContext.request.contextPath}/res/img/pixel.png" />
			</a></li>
		</ul>
	</div>
</div>
