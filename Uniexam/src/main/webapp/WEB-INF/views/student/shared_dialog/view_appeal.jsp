<%@page import="it.unical.uniexam.DateFormat"%>
<%@page import="java.util.Set"%>
<%@page import="it.unical.uniexam.MokException"%>
<%@page import="it.unical.uniexam.hibernate.domain.Student"%>
<%@page import="it.unical.uniexam.hibernate.domain.AppealStudent"%>
<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="it.unical.uniexam.hibernate.domain.RequestedCourse"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	$(document).ready(function(){
		dialogViewAppeal();
	//	initCollapsable();
	});
	
function inscription(id) {
	$("<div>Vuoi iscriverti all'esame?</div>").attr('id',"divInscription").appendTo('body');
	$("#divInscription").attr("title",'Inscription?');
	$("#divInscription").dialog({
	      resizable: false,
	      modal: true,
	      buttons: {
	        "Confirm": function() {
	        	var ajax=sendAJAXmessage($("#context").attr("value")+"/ajax/appeal/inscriptionToAppeal","GET","id",id);
	        	ajax.done(function(msg){
	        		if(msg.match("no")){
	        			alert("Errore! Iscrizione non riuscita");
	        		}else{
	        			alert("Iscrizione riuscita");
	        			window.location=$("#context").attr("value")+"/course";
	        		}
	        	});
	         	$( this ).dialog( "close" );
	         	$("div").remove("#divInscription");
	        },
	        Cancel: function() {
	          	$( this ).dialog( "close" );
	        	$("div").remove("#divInscription");
	        }
	      },
	      close:function(){
				$( this ).dialog( "close" );
				$("div").remove("#divInscribe");
			}
	    });
	$("#divInscription").attr("title","");	
}

function removeInscription(id) {
	$("<div>Vuoi cancellare l'iscrizione all'esame?</div>").attr('id',"divRemoveInscription").appendTo('body');
	$("#divRemoveInscription").attr("title",'Remove inscription?');
	$("#divRemoveInscription").dialog({
	      resizable: false,
	      modal: true,
	      buttons: {
	        "Confirm": function() {
	        	var ajax=sendAJAXmessage($("#context").attr("value")+"/ajax/appeal/removeInscription","GET","id",id);
	        	ajax.done(function(msg){
	        		if(msg.match("no")){
	        			alert("Errore! Cancellazione non riuscita");
	        		}else{
	        			alert("Cancellazione riuscita");
	        			window.location=$("#context").attr("value")+"/course";
	        		}
	        	});
	         	$( this ).dialog( "close" );
	         	$("div").remove("#divRemoveInscription");
	        },
	        Cancel: function() {
	          	$( this ).dialog( "close" );
	        	$("div").remove("#divRemoveInscription");
	        }
	      },
	      close:function(){
				$( this ).dialog( "close" );
				$("div").remove("#divRemoveInscription");
			}
	    });
	$("#divRemoveInscription").attr("title","");	
}
</script>

<div id="dialog_content" align="center">
	<%
	Student student = (Student) request.getAttribute("I");
	ArrayList<Appeal> appeal = (ArrayList<Appeal>) request.getAttribute("appeal");
	ArrayList<AppealStudent> appealStudent = (ArrayList<AppealStudent>) request.getAttribute("as");
	boolean isInscribed = false;
	Long idAppealStudent = null;
	String vote = null;
	Set<RequestedCourse> requestCourses = (Set<RequestedCourse>) request.getAttribute("rc");
	if(appeal!=null && !appeal.isEmpty()) {
		%>
		<label><%=appeal.get(0).getCourse().getName()%></label>
		<br><br>
		<table border="1">
			<thead>
				<tr>
					<th rowspan="2">Prova</th>
					<th>Data Esame</th>
					<th rowspan="2">Nr. iscritti</th>
					<th>Apertura</th>		
					<th rowspan="2">Iscritto</th>
					<th rowspan="2">Voto</th>
 					<th rowspan="2"><th>
				</tr>
				<tr>
					<th>Luogo</th>
					<th>Chiusura</th>
				</tr>
			</thead>
				<%
				for(Appeal a : appeal) {
					%>
					<tr>
						<td rowspan="2"><%=a.getName()%></td>
						<td align="center"><%=DateFormat.getDayMonthYear(a.getExamDate())%> <%=DateFormat.getHourString(a.getExamDate())%>:<%=DateFormat.getMinuteString(a.getExamDate())%></td>
						<td rowspan="2" align="center"><%=a.getCurrNumberOfInscribed()%>/<%=a.getMaxNumberOfInscribed()%></td>
						<td align="center"><%=DateFormat.getDayMonthYear(a.getOpenDate())%> <%=DateFormat.getHourString(a.getOpenDate())%>:<%=DateFormat.getMinuteString(a.getOpenDate())%></td>
						<%
						if(appealStudent!=null && !appealStudent.isEmpty()) {
							for(AppealStudent as : appealStudent) {
								try {
									if(a.getId()==as.getAppeal().getId()) {
										if(as.getStudent().getId()==student.getId()) {
											isInscribed=true;
											idAppealStudent = as.getId();
											if(as.getTemporany_vote()>30.0)
												vote = "30 L";
											else
												vote = String.valueOf(as.getTemporany_vote().intValue());
										}
									}
								} catch (Exception e) {
									new MokException(e);
								}
							}					
						}
						if(requestCourses!=null && !requestCourses.isEmpty()) {
							%>
							<td rowspan="2" align="center">-</td>
							<td rowspan="2" align="center">-</td>
							<td rowspan="3"><img src="${pageContext.request.contextPath}/res/img/stop.png" style="height: 60px; width: 60px"></td>							
							<%
						}
						else {
							if(isInscribed) {
								isInscribed = false;
							%>
								<td rowspan="2" align="center">SI</td>
								<td rowspan="2" align="center"><%=vote!=null ? vote : "-"%></td>
								<td rowspan="3"><div class="bottonmok color_red" onclick="removeInscription('<%=idAppealStudent%>')">Cancella</div></td>
							<%
								idAppealStudent = null;
								vote = null;
							} else {
								%>
								<td rowspan="2" align="center">NO</td>
								<td rowspan="2" align="center">-</td>
								<td rowspan="3"><div class="bottonmok" onclick="inscription('<%=a.getId()%>')">Iscrivi</div></td>
							<%							
							}
						}
							%>
					</tr>
					<tr>
						<td><%=a.getLocation()%></td>
						<td align="center"><%=DateFormat.getDayMonthYear(a.getCloseDate())%> <%=DateFormat.getHourString(a.getCloseDate())%>:<%=DateFormat.getMinuteString(a.getCloseDate())%></td>
					</tr>					
					<tr>
						<td colspan="6"><%=a.getDescription()%></td>
					</tr>
					<tr>
						<td colspan="7"></td>
					</tr>
					<%
				}
				%>
			</table>
			<%
			} else {
				%>
				<label>Non ci sono appelli</label>
				<%
			}
			%>
			<%
			if(requestCourses!=null && !requestCourses.isEmpty()) {
				%>
				<br><br>
				<label>Sono presenti delle propedeuticita'. Contattare il docente per maggiori informazioni</label><br>
				<%
			}
			%>
</div>