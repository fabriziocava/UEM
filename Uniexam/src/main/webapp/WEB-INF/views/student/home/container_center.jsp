<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	function ticker(){
	    $('#ticker li:first').slideUp(
	    function () { $(this).appendTo($('#ticker')).slideDown(); });
	}
	setInterval(function(){ ticker()}, 3000);
</script>

<div class="container-center">
	<%
	ArrayList<String> news = (ArrayList<String>) request.getAttribute("news");
	%>
	<table>
		<tr>
			<td width="80%" valign="top">
				<fieldset>
					<legend>News</legend>
					<%
					if(news!=null && !news.isEmpty()) {
					%>
				  		<ul id="ticker" class="ticker">
				  			<% 
				  			for(String n : news) {
				  				%>
				  				<li><%=n%></li>
				  				<%
				  			}
				  			%>
				  		</ul>
				  	<%} else {%>
				  		<label>Non ci sono news!</label>
				  	<%}%>
			  	</fieldset>
			</td>
			<td width="20%">
				<img src="${pageContext.request.contextPath}/res/img/community_news.png">
			</td>
		</tr>
	</table>
</div>
