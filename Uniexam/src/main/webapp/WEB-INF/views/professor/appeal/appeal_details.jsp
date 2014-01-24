<%@page import="it.unical.uniexam.hibernate.domain.Appeal"%>
<%@page import="javassist.expr.NewArray"%>
<%@page import="it.unical.uniexam.hibernate.domain.Group"%>
<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- from Controller attribute course that contains the all details for Course -->
<%

		Appeal appeal=(Appeal)request.getAttribute("appeal");
	%>

<div>
<script type="text/javascript">
function sendDeleteAppeal(id){
	$("<div>Sei sicuro di voler eliminare l'appello?</div>").attr('id',"divDelete").appendTo('body');
	$("#divDelete").attr("title",'Delete?');
	$("#divDelete").dialog({
	      resizable: false,
	      modal: true,
	      buttons: {
	        "Confirm": function() {
	        	var ajax=sendAJAXmessage($("#context").attr("value")+"/ajax/appeal/remove_appeal","GET","id",id);
	        	ajax.done(function(msg){
	        		if(msg.match("no")){
	        			alert("Errore! non ho potuto eliminare l'appello");
	        		}else{
	        			alert("Appello eliminato");
	        			window.location=$("#context").attr("value")+"/appeal";
	        		}
	        	});
	         	$( this ).dialog( "close" );
	         	$("div").remove("#divDelete");
	        },
	        Cancel: function() {
	          	$( this ).dialog( "close" );
	        	$("div").remove("#divDelete");
	        }
	      },
	      close:function(){
				$( this ).dialog( "close" );
				$("div").remove("#divDelete");
			}
	    });
	$("#divDelete").attr("title","");
}
</script>
<table>

<tr title="<spring:message code='message.professor.appeal.add_appeal.name.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.name'/></th><td>
<div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','name','String')">
<%=appeal.getName() %></div></td>
</tr>

<tr title="<spring:message code='message.professor.appeal.add_appeal.description.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.description'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','description','String')">
<%=appeal.getDescription() %></div></td>
</tr>

<tr title="<spring:message code='message.professor.appeal.add_appeal.location.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.location'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','location','String')">
<%=appeal.getLocation() %></div>
</td>
</tr>
<%if(appeal.getCourse()!=null){ %>
<tr>
<th><spring:message code='label.course'/></th>
<td><%=appeal.getCourse().getName() %></td>
</tr>
<%} %>

<tr title="<spring:message code='message.professor.appeal.add_appeal.maxNumberOfInscribed.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.maxNumberOfInscribed'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','maxNumberOfInscribed','Integer')">
<%=appeal.getMaxNumberOfInscribed() %></div></td>
</tr>

<tr title="<spring:message code='message.professor.appeal.add_appeal.openDate.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.openDate'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','openDate','Date')">
<%=appeal.getOpenDate() %></div></td>
</tr>

<tr title="<spring:message code='message.professor.appeal.add_appeal.closeDate.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.closeDate'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','closeDate','Date')">
<%=appeal.getCloseDate() %></div></td>
</tr>

<tr title="<spring:message code='message.professor.appeal.add_appeal.examDate.description'/>">
<th><spring:message code='message.professor.appeal.add_appeal.examDate'/></th>
<td><div 
contenteditable="true" 
onfocus="storeOld(this)" 
onblur="checkBeforeChangeEditable(this,'appeal/modify_appeal','<%=appeal.getId()%>','examDate','Date')">
<%=appeal.getExamDate() %></div></td>
</tr>

<tr>
<td><div class="line-top"></div></td>
<td><div class="bottonmok color_red" onclick="sendDeleteAppeal('<%=appeal.getId()%>')"><spring:message code="message.general.delete" /></div></td>
</tr>
</table>

</div>