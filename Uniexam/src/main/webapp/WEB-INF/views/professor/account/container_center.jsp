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
	<div style="display: inline-block;">
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
			<div style="float: right; margin-left: 30px">
				<fieldset>
					<legend>
						<spring:message
							code="message.professor.account.container.center.legend" />
					</legend>
					<article>
						<form action="${pageContext.request.contextPath}/professor/upload"
							method="POST" enctype="multipart/form-data">
							<section>
								<table class="tablemok">
									<tr>
										<td>
											<div style="">Please select a file to upload :</div>
										<td>
									</tr>
									<tr>
										<td><input type="file" name="file" />
										<td>
									</tr>
									<tr>
										<td><input type="submit" value="upload" />
										<td>
									</tr>
								</table>
							</section>
						</form>
					</article>
				</fieldset>
			</div>
		</div>
	</div>
</div>
