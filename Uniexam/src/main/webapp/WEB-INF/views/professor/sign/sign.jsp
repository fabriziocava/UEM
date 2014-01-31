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
		selectingFromDashBoard(document.getElementById("signButton"));
	});
	// 	getDataFromAjax('','no','container-center')
</script>
<div class="container-center">
	<script type="text/javascript">
		$(document).ready(function() {
			$(".container-center").attr('id', 'container-center');
		});
		function dialogFromAppealPrepare() {
			var dial = openDialogFromDiv('from_appeal', "<spring:message code='message.professor.fromappeal'/>");
			var buttons = {};
			buttons.Seleziona = function() {
				var idAppeal=$("#appealID").attr('value');
				if(idAppeal!=undefined){
					var conte=$("#context").attr("value");
					window.location=conte+"/sign/prepare_from_appeal?id="+idAppeal;
					dial.dialog("close");
				}
			};
			dial.dialog("option", "buttons", buttons);
		}
		function dialogFromAppealSign() {
			var dial = openDialogFromDiv('from_appeal', "<spring:message code='message.professor.fromappeal'/>");
			var buttons = {};
			buttons.Seleziona = function() {
				var idAppeal=$("#appealID").attr('value');
				if(idAppeal!=undefined){
					var conte=$("#context").attr("value");
					window.location=conte+"/sign/from_appeal_sign?id="+idAppeal;
					dial.dialog("close");
				}
			};
			dial.dialog("option", "buttons", buttons);
		}
	</script>

	<div id="from_appeal" class="startHide">
		<input type="text" min="3" maxlength="8" id="idComplete" placeholder="<spring:message code='message.professor.name.room.course'/>"
			onkeyup="ajaxAutoComplete('/ajax/sign/list_appeals',this.value,'risTable')" 
			onblur="clearContent('risTable')"/>
		<input id="appealID" name="appealID" type="hidden" />
		<div>
			<table class="table-list tablemok"
				style="background-color: white; display: table-row-group"
				id="risTable">
			</table>
		</div>
	</div>

	<div class="aligncenter">
		<ul class="links-user" style="margin: 0px; padding: 0px;">
			<li>
				<fieldset>
					<legend><spring:message code='message.professor.prepare'/></legend>
					<buttonmok onclick="dialogFromAppealPrepare()"><spring:message code='message.professor.fromappeals'/></buttonmok>
					<br />
					<buttonmok onclick="go('/sign/singleprepare')"><spring:message code='message.professor.siglesign'/></buttonmok>
				</fieldset>
			</li>

			<li>
				<fieldset>
					<legend><spring:message code='message.professor.sign'/></legend>
					<buttonmok onclick="dialogFromAppealSign()"><spring:message code='message.professor.fromappeals'/></buttonmok>
					<br />
					<buttonmok onclick="go('/sign/singlesign')"><spring:message code='message.professor.siglesign'/></buttonmok>
				</fieldset>
			</li>
			<li>
				<fieldset>
					<legend><spring:message code='message.professor.signascommissionar'/></legend>
					<buttonmok onclick="go('/sign/signAsCommissionar')"><spring:message code='message.professor.siglesign'/></buttonmok>
				</fieldset>
			</li>
		</ul>
	</div>

</div>
