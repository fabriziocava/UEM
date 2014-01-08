<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<div class="header-logo">
		<a href="${pageContext.request.contextPath}/professor/home"
			title="home"> <img
			src="${pageContext.request.contextPath}/res/img/pixel.png" width="30"
			height="30">
		</a>
	</div>
	<span style="float: left; position: relative; margin-left: 30px;">
		<a href="?lang=en">en</a> | <a href="?lang=it">it</a>
	</span>
	<div>
		<ul class="links-user">
			<li><img src="imageUser" />${I.getName()}</li>
			<li><a
				href="${pageContext.request.contextPath}/professor/personalizzation">
					<img class="personalizzation-header"
					src="${pageContext.request.contextPath}/res/img/pixel.png" />
			</a></li>
			<li><a href="${pageContext.request.contextPath}/logout"> <img
					class="logout-header"
					src="${pageContext.request.contextPath}/res/img/pixel.png" />
			</a></li>
		</ul>
	</div>
</div>
