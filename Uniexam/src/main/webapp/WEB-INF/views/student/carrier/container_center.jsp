<%@page import="it.unical.uniexam.DateFormat"%>
<%@page import="it.unical.uniexam.hibernate.domain.Carrier"%>
<%@page import="it.unical.uniexam.MokException"%>
<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="java.util.Set"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function() {
		selectingFromDashBoard(document.getElementById("carrierButton"));
		$("#sorting").tablesorter();
	});
</script>

<div class="container-center">
	<fieldset>
		<legend><spring:message code='label.carrier'/></legend>
		<%
		ArrayList<Carrier> carrier = (ArrayList<Carrier>) request.getAttribute("carrier");
		ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
		Double weightedVote = 0.0;
		Double nCredits = 0.0;
		Integer vote = 0;
		if((carrier!=null && !carrier.isEmpty())||(appealStudent!=null && !appealStudent.isEmpty())) {
			%>
				<table border="1" style="width: 100%">
					<thead>
						<tr>
							<th><spring:message	code="label.code" /></th>
							<th><spring:message	code="label.degreeCourse" /></th>
							<th><spring:message	code="label.course" /></th>
							<th><spring:message	code="message.professor.course.attribute.credits" /></th>
							<th><spring:message	code="label.vote" /></th>		
							<th><spring:message	code="label.date" /></th>
						</tr>
					</thead>			
			<%
				for(Carrier c: carrier) {
					try {
						%>
						<tr>
							<td align="center"><%=c.getCourse().getCode()%></td>
							<td align="center"><%=c.getCourse().getDegreeCourse().getId()%></td>
							<td><%=c.getCourse().getName()%> (LOADED IN SECRETARY)</td>
							<td align="center"><%=c.getCourse().getCredits()%></td>
							<td align="center"><%=c.getVote()>30 ? "30 L" : c.getVote()%></td>
							<td align="center"><%=DateFormat.getDayMonthYear(c.getDate())%></td>
						</tr>
						<%
						if(c.getVote()>30)
							vote=30;
						else
							vote=c.getVote();
						weightedVote += vote*c.getCourse().getCredits();
						nCredits += c.getCourse().getCredits();
					} catch (Exception e) {
						new MokException(e);
					}					
				}
				Course course = null;
				for(AppealStudent as : appealStudent) {
					try {
						if(as.getAppeal()!=null)
							course = as.getAppeal().getCourse();
						else if(as.getCourse()!=null)
							course = as.getCourse();
						if(course!=null) {
							%>
							<tr>
								<td align="center"><%=course.getCode()%></td>
								<td align="center"><%=course.getDegreeCourse().getId()%></td>
								<td><%=course.getName()%> (<%=as.getState()%>)</td>
								<td align="center"><%=course.getCredits()%></td>
								<td align="center"><%=as.getTemporany_vote()>30.0 ? "30 L" : as.getTemporany_vote().intValue()%></td>
<!-- INSERIRE DATA IN APPEAL-STUDENT -->
								<td align="center"><%=DateFormat.getDayMonthYear(as.getAppeal().getExamDate())%></td>
							</tr>
							<%
						}
					} catch (Exception e) {
						new MokException(e);
					}
				}
			%>
				</table>
				<br>
				<%
				Double avarage = weightedVote/nCredits;
				if(weightedVote==0.0 || nCredits==0.0)
					avarage = 0.0;
				if(avarage>30.0)
					avarage = 30.0;
				%>
				<table>
					<tr>
						<td align="left"><label>Media su base esame:</label></td>
						<td align="right"><label><%=avarage%> / 30</label></td>
					</tr>
					<tr>
						<td align="left"><label>Media su base titolo:</label></td>
						<td align="right"><label><%=avarage*110/30%> / 110</label></td>
					</tr>
				</table>
			<%
		} else {
			%>
			<label>Non hai esami in carriera</label>
			<%
		}
		%>
	</fieldset>
	<br><br>
	<fieldset>
		<legend><spring:message code='label.legend'/></legend>
		<table style="width: 100%">
			<tr>
				<td><label style="color: red;">NON ANCORA FIRMATO DIGITALMENTE DAL DOCENTE</label></td>
			</tr>
			<tr>
				<td style="padding-left: 1.5em;" ><label style="text-align: justify; font-size: 10pt">il presidente della commissione ha inserito il verbale d'esame in Uniexam ma non ha ancora provveduto a firmare digitalmente lo stesso.</label></td>
			</tr>
			<tr>
				<td><label style="color: red;">NON ANCORA FIRMATO DIGITALMENTE DAL COMMISSARIO / SEGRETARIO</label></td>
			</tr>
			<tr>
				<td style="padding-left: 1.5em;" ><label style="text-align: justify; font-size: 10pt">il commissario non ha ancora firmato digitalmente l'esame.</label></td>
			</tr>
			<tr>
				<td><label style="color: red;">PER IL PASSAGGIO IN CARRIERA LO STUDENTE DEVE PRENDERE VISIONE DEL VERBALE</label></td>
			</tr>
			<tr>
				<td style="padding-left: 1.5em;" ><label style="text-align: justify; font-size: 10pt">lo studente deve apporre la propria password mediante la funzione di "VERBALI DA FIRMARE" per permettere il passaggio dell'esame nella carriera ufficiale della Segreteria Studenti.</label></td>
			</tr>
			<tr>
				<td><label style="color: green;">ESAME CARICATO IN SEGRETERIA</label></td>
			</tr>
			<tr>
				<td style="padding-left: 1.5em;" ><label style="text-align: justify; font-size: 10pt">l'esame è stato caricato nella propria carriera ufficiale in Segreteria Studenti.</label></td>
			</tr>
		</table>
	</fieldset>
</div>	