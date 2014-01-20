<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("courseButton"));
	});
</script>
<div class="container-center">
	<script type="text/javascript">
		
	</script>
	<div>
		<div>
			<div style="float: left;">
				<img class="image360"
					src="${pageContext.request.contextPath}/professor/image" />
				<%
					
				%>
				<%
					
				%>
				<%
					
				%>
			</div>
			<div style="float: right:;">
				<fieldset>
					<legend>
						<spring:message
							code="message.professor.account.container.center.legend" />
					</legend>
					<article>
						<section id="">
							<form
								action="${pageContext.request.contextPath}/professor/upload"
								method="POST" enctype="multipart/form-data">
								Please select a file to upload : <input type="file" name="file" />
								<input type="submit" value="upload" />
							</form>
						</section>
					</article>
				</fieldset>
			</div>
		</div>
	</div>
</div>
