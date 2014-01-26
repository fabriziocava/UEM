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
		function openDialogFromDiv(idDiv, title) {
			var dial = $("#" + idDiv);
			dial.attr("title", title);
			dial.dialog({
				autoOpen : true,
				modal : true,
				width : "auto",
				show : {
					effect : "blind",
					duration : 500
				},
				hide : {
					effect : "explode",
					duration : 500
				},
				close : function() {
					dial.dialog("close");
				}
			});
			dial.attr("title", "");
			return dial;
		}

		function dialogFromAppeal() {
			var dial = openDialogFromDiv('from_appeal', 'From Appeal');
			var buttons = {};
			buttons.Seleziona = function() {
				var idAppeal=$("#appealID").attr('value');
				if(idAppeal!=undefined){
// 					getDataFromAjaxForce("sign/prepare_from_appeal",idAppeal,'container-center');
					var conte=$("#context").attr("value");
					window.location=conte+"/sign/prepare_from_appeal?id="+idAppeal;
					dial.dialog("close");
				}
			};
			dial.dialog("option", "buttons", buttons);
		}
	</script>

	<div id="from_appeal" style="display: none;">
		<input type="text" min="3" maxlength="8" 
		id="idComplete" 
		onkeyup="ajaxAutoComplete('/ajax/sign/list_appeals',this.value,'risTable')" /> 
			<input id="appealID" name="appealID" type="hidden" />
		<div>
			<table class="tablemok"
				style="background-color: white; display: table-row-group"
				id="risTable">
			</table>
		</div>
	</div>

	<div class="aligncenter">
		<ul class="links-user" style="margin: 0px; padding: 0px;">
			<li>
				<fieldset>
					<legend>Prepare</legend>
					<buttonmok onclick="dialogFromAppeal()">From Appeals</buttonmok>
					<br />
					<buttonmok>Single Sign</buttonmok>
				</fieldset>
			</li>

			<li>
				<fieldset>
					<legend>Sign</legend>
					<buttonmok>From Appeals</buttonmok>
					<br />
					<buttonmok>Single Sign</buttonmok>
				</fieldset>
			</li>
		</ul>
	</div>

</div>
