<%@page import="it.unical.uniexam.hibernate.domain.Course"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.PhoneNumber"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.Email"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.PhoneNumber.TYPE"%>
<%@page import="it.unical.uniexam.hibernate.domain.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%
Long idCourse=(Long)request.getAttribute("idCourse");
ArrayList<Professor>coms=(ArrayList<Professor>)request.getAttribute("commissionary");
if(coms!=null && coms.size()>0){
%>

<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
	function actionAddCommissary(idCourse,idProf){
		names=new Array();
		names.push("idCourse");
		values=new Array();
		values.push(idCourse);
		names.push("idProf");
		values.push(idProf);
		var path="/ajax/course/dialog/add_commissionary";
		var type="POST";
		var dati=new FormData();
		for(var i=0; i<names.length;i++){
			dati.append(names[i],values[i]);
		}
		var ajax=sendAJAXmessage3(path, type, dati);
		ajax.done(function(data){
			$("#dialog"+dialogCount).html(data);
		});
	}
	function dialogForSearchCommissary() {
		var dial = openDialogFromDiv('search_commissionary', "Search Commissary");
		var buttons = {};
		buttons.Seleziona = function() {
			var idProf=$("#professorID").attr('value');
			if(idProf!=undefined){
				actionAddCommissary('<%=idCourse%>',idProf);
				dial.dialog("close");
			}
		};
		dial.dialog("option", "buttons", buttons);
	}
</script>

<div id="search_commissionary" class="startHide">
		<input type="text" min="3" maxlength="8" id="idCompleteProfessor" placeholder="Name, Surname"
			onkeyup="ajaxAutoComplete('/ajax/course/list_professor',this.value,'risTable')" 
			onblur="clearContent('risTable')"/>
		<input id="professorID" name="professorID" type="hidden" />
		<div>
			<table class="table-list tablemok"
				style="background-color: white; display: table-row-group"
				id="risTable">
			</table>
		</div>
	</div>
	
<div id="dialog_content">
<div class="aligncenter">
<table class="tablemok morespace">
<tr>
<th>Nome</th><th>web site</th><th>phone</th><th>email</th>
</tr>
<%for(Professor c:coms){ %>
<%
Email e=c.getEmail(it.unical.uniexam.hibernate.domain.utility.Email.TYPE.UNUFFICIAL);
String email="No email";
if(e!=null)
	email=e.getEmail();
PhoneNumber p=c.getPhoneNumber(TYPE.UFFICIAL);
String number="No number";
if(p!=null)
	number=p.getNumber();
%>
<tr>
<td><%=c.getName() %> , <%=c.getSurname() %></td>
<td><%=c.getWebSite() %></td>
<td><%=number %></td>
<td><%=email %></td>
<% %>
<% %>
</tr>
<%} %>
</table>
</div>
<%}else{%>
<div class="aligncenter">
No Commissary
</div>
<% }%>
<div class="aligncenter">
<buttonmok onclick="dialogForSearchCommissary()">Aggiungi commissario</buttonmok>
</div>
<%%>
</div>