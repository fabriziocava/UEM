$(document).ready(function() {
//	alineamentoContainer();
//	$(documet).tooltip();
	alingDashBoard();
});

$(document).ready(function() {
	initDraggable();
//	initCollapsable();
});

$(document).ready(function() {
	$(".box-header").css("width","+=10px");
	var conte=$("#contextPath").attr("value");
	$(".processing").children().attr("src",conte+"/res/img/spinner36_39.gif");
	$(".box").bind("click",function(){
		$(".box").css("z-index","2");
		$(this).css("z-index","3");
	});
});

//titleidOrClass è o l'id o la classe dove viene assegnato il mouse enter
//titledivid è l'ID del div che si apparirà dal mouse 
function titlemok(titleidOrClass,titledivid){
	var timer;
	var delay=500;
	$('.'+titleidOrClass).bind('mouseenter',function(event) {
		timer = setTimeout(function() {
			$("#"+titledivid).css("left",event.pageX);
			$("#"+titledivid).css("top",event.pageY);
			$("#"+titledivid).fadeIn();
			$("#"+titledivid).attr("display","block");
		}, delay);
	    
	});
	$('#'+titleidOrClass).bind('mouseenter',function(event) {
		timer = setTimeout(function() {
			$("#"+titledivid).css("left",event.pageX);
			$("#"+titledivid).css("top",event.pageY);
			$("#"+titledivid).fadeIn();
			$("#"+titledivid).attr("display","block");
		}, delay);
	    
	});
	$('#'+titleidOrClass).bind('mouseleave',function(event) {
		timer = setTimeout(function() {
			$("#"+titledivid).fadeOut();
			clearTimeout(timer);
		}, delay);
	    
	});
	$('.'+titleidOrClass).bind('mouseleave',function(event) {
		timer = setTimeout(function() {
			$("#"+titledivid).fadeOut();
			clearTimeout(timer);
		}, delay);
	    
	});
}

var oldString = "";
function storeOld(item) {
	oldString = item.innerHTML;
}
function checkBeforeChangeEditable(item,path,id,variable,clazz) {
	var newString = item.innerHTML;
	if (newString == oldString || newString == "") {
		return;
	}
	if(newString.length>254){
		var differenza=newString.length-254;
		alert("Il testo non può superare i 254 caratteri!\n devi eliminare almeno "+differenza);
		return;
	}
	changeEditable(path,id,variable,newString,clazz);
//	changeNote(item, idCourse);
}

function changeEditable(path,id,variable,newString,clazz){
	var conte=$("#context").attr("value");
	var ing=$.ajax({
		type: "POST",
		url: conte+"/ajax/"+path,
		data:{ id: id, variable: variable,value:newString,clazz:clazz}
	});
	$(".processing").css("display","block");
	ing.done(function(msg){
		$(".processing").css("display","none");
		if(msg.match("no")){
			alert("Errore nel apportare le modifiche");
		}
	});
	return ing;
}

//apre un popUp con il contenuto quello che si vuole nel case
//quindi crea il nuovo div e e gli mette dentro i data provenienti dal COntroller
//d'altra parte nei data deve esserci la gestione del dialog
// importante alla chiusura di esso deve eliminare l'elemento creato qui
//ovvero $("div").remove("#dialog");
function openPopUpWithAjaxContent(caseId,id){
	if(caseId.match("requested_course")){
		var conte=$("#context").attr("value");
		var ajax=sendAJAXmessage(conte+"/ajax/dialog/requested_course", "GET", "id", id);
		ajax.done(function(data){
			if($("#dialog").html()==undefined)
				$("<div></div>").attr('id','dialog').appendTo('body');
			$("#dialog").html(data);

		});
	}else if(caseId.match("addAppeal")){
		var conte=$("#context").attr("value");
		var ajax=sendAJAXmessage(conte+"/ajax/dialog/add_appeal", "GET", "i", "0");
		ajax.done(function(data){
			if($("#dialog").html()==undefined)
				$("<div></div>").attr('id','dialog').appendTo('body');
			$("#dialog").html(data);
		});
	}else if(caseId.match("viewAppeal")){
		var conte=$("#context").attr("value");
		var ajax=sendAJAXmessage(conte+"/ajax/dialog/view_appeal", "GET", "id", id);
		ajax.done(function(data){
			if($("#dialog").html()==undefined)
				$("<div></div>").attr('id','dialog').appendTo('body');
			$("#dialog").html(data);
		});
	}else if(caseId.match("viewListStudent")){
		var conte=$("#context").attr("value");
		var ajax=sendAJAXmessage(conte+"/ajax/dialog/list_student", "GET", "id", id);
		ajax.done(function(data){
			if($("#dialog").html()==undefined)
				$("<div></div>").attr('id','dialog').appendTo('body');
			$("#dialog").html(data);
		});
	}
	
}

function dialogViewListStudent(){
	$("#dialog").attr("title","List Student");
	$("#dialog").dialog({
		autoOpen : true,
		modal: true,
		width:"auto",
		show : {
			effect : "blind",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 500
		},
		close:function(){
			$( this ).dialog( "close" );
			$("div").remove("#dialog");
			commands=undefined;
		}
	});
	$("#dialog").attr("title","");
}


function dialogViewAppeal(){
	$("#dialog").attr("title","View Appeal");
	$("#dialog").dialog({
		autoOpen : true,
		modal: true,
		width:"auto",
		show : {
			effect : "blind",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 500
		},
		close:function(){
			$( this ).dialog( "close" );
			$("div").remove("#dialog");
			commands=undefined;
		}
	});
	$("#dialog").attr("title","");
}

function dialogAddAppeal(){
	$("#dialog").attr("title","Add Appeal");
	$("#dialog").dialog({
		autoOpen : true,
		modal: true,
		width:"auto",
		show : {
			effect : "blind",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 500
		},
		close:function(){
			$( this ).dialog( "close" );
			$("div").remove("#dialog");
			commands=undefined;
		}
	});
	$("#dialog").attr("title","");
}


function Commands(name,id){
	// scrivere la classe che prende in input il set a command e poi con un to string ritorna la stringa come commando
	this.name=name;
	this.id=id;
	this.id1=new Array();
	this.command=new Array();
	this.id2=new Array();
	
	this.add=function(id11,command2,id22){
		for(var i=0;i<this.id1.length;i++){
			if(this.id1[i]==id11){
				this.command[i]=command2;
				this.id2[i]=id22;
				return 0;
			}
		}
		this.id1.push(id11);
		this.command.push(command2);
		this.id2.push(id22);
		return 1;
	};
	this.toString=function(){
		var res="";
		for(var i=0;i<this.id1.length;i++){
			if(res==""){
				res=this.name+this.id+":";
			}
			res=res+this.id1[i]+","+this.command[i]+","+this.id2[i]+":";
		}
		return res;
	};
}

function dirtingTheElement(){
	for(var i=0;i<commands.id1.length;i++){
		if($("#requestedCourse"+commands.id1[i]).val()!=undefined){
			if(!$("#requestedCourse"+commands.id1[i]).html().match("\\*"))
				$("#requestedCourse"+commands.id1[i]).append("*");
		}
	}
}

function submitCommandRequestedCourse(){
	var data=commands.toString();
	var conte=$("#context").attr("value");
	var ajax=sendAJAXmessage(conte+"/ajax/dialog/requested_course/command", "POST", "data", data);
	ajax.done(function(data){
		if($("#dialog").html()==undefined)
			$("<div></div>").attr('id','dialog').appendTo('body');
		$("#dialog").html(data);
	});
//	alert(data);
}

function submitAddRequestedCourse(){
	var conte=$("#context").attr("value");
//	$("#addrequestedCourse").submit();
	var ajax=$.post(conte+'/addRequestedCourseAction', $('#addrequestedCourse').serialize());
	ajax.done(function(data){
		if($("#dialog").html()==undefined)
			$("<div></div>").attr('id','dialog').appendTo('body');
		$("#dialog").html(data);
	});
}

function dialogModifyRequestedCourse(){
	$("#dialog").attr("title","Requested Course");
	$("#dialog").dialog({
		autoOpen : true,
		modal: true,
		width:"auto",
		show : {
			effect : "blind",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 500
		},
		close:function(){
			$( this ).dialog( "close" );
			$("div").remove("#dialog");
			commands=undefined;
		}
	});
	$("#dialog").attr("title","");
	
	$("li[id^='modifyRequest']").bind("click", function(event) {
		var ids=this.id;
		ids=ids.replace("modifyRequest","");
		var id=ids.split("$")[0];
		var degree=ids.split("$")[1];
		var idCourse=ids.split("$")[2];
//		alert("id is: "+id+" and degree is: "+degree);
//		if($("#sendRequestCourseChange").html()==undefined){
//			$("<input></input>").attr('id','sendRequestCourseChange').attr('name','setCommand').appendTo('body');
			try{
			if(commands==null);
//				commands=new Commands("sendRequestedCourse",idCourse);
			}catch(ERR){
				commands=new Commands("sendRequestedCourse",idCourse);
			}
//		}
//			creare il div che apparirà d'avanti al mouse
		$("<div></div>")
		.attr('id','divRequestCourseChange')
		.appendTo('body').html($("#radio"+degree).html());
		$("#divRequestCourseChange").attr("title",$(this).html());
		$("#divRequestCourseChange").dialog({
		      resizable: false,
		      modal: true,
		      buttons: {
		        "Save": function() {
		          var newVal=$("input[name='choose']:radio:checked").val();
		          commands.add(id, "change", newVal);
		          $(".alertSomeModifyRequestCourse").each(function(){
		        	 $(this).slideDown(); 
		          });
		          dirtingTheElement();
//		          requested_courseAddIfNotAddAlready ///strutturaaaa!!! classeEEEEE
//		          var comm="%requested"+id+"$change"+newVal+"%";
		          $( this ).dialog( "close" );
		          $("div").remove("#divRequestCourseChange");
		        },
		        Cancel: function() {
		          $( this ).dialog( "close" );
		        	if($("#sendRequestCourseChange").val()==""){
		        		$("input").remove("#sendRequestCourseChange");
		        	}
		        	$("div").remove("#divRequestCourseChange");
		        }
		      },
		      close:function(){
					$( this ).dialog( "close" );
					$("div").remove("#divRequestCourseChange");
				}
		    });
		$("#divRequestCourseChange").attr("title","");
		$("#setRequestCourseChange").css('height',"auto");
	});
	$("li[id^='deleteRequest']").bind("click", function(event) {
		var ids=this.id;
		ids=ids.replace("deleteRequest","");
		var id=ids.split("$")[0];
		var idCourse=ids.split("$")[1];
		try{
			if(commands==null);
		}catch(ERR){
			commands=new Commands("sendRequestedCourse",idCourse);
		}
		 commands.add(id, "remove", "no");
         $(".alertSomeModifyRequestCourse").each(function(){
        	 $(this).slideDown(); 
         });
         dirtingTheElement();
	});
	$("div[id^='addRequested']").bind("click", function(event) {
		var conte=$("#context").attr("value");
		var id=this.id;
		var idCourse=id.replace("addRequested","");
		var ajax=sendAJAXmessage(conte+"/ajax/dialog/addRequestedCourse", "GET", "id", idCourse);
		ajax.done(function(data){
			if($("#dialogAddRequested").html()==undefined)
				$("<div></div>").attr('id','dialogAddRequested').appendTo('body');
			$("#dialogAddRequested").html(data);
		});
	});
}

function dialogAddRequestedCourse(){
	
}

function getDataFromAjax(pathRequ,id,idDest){
//	var id=item.id;
//	if(id.match(caseId)){
		if($("#"+idDest).html()==""){
			var conte=$("#context").attr("value");
			var ajax=sendAJAXmessage(conte+"/ajax/"+pathRequ, "GET", "id", id);
			ajax.done(function(data){
				$("#"+idDest).html(data);
			});
		}else{
//			$("#"+idDest).delay(10).slideToggle(500);
		}
//	}
}

/**
 * <span class="span_expandible" onclick="collapseMok(this); otherF();" id="collapseIDTAGTOCOLLAPSE">+</span>	
 */
function collapseMok(item){
		var idOld = item.id;
		var realID;
		var newID;
		if(idOld.match("collapse")){
			realID = idOld.replace("collapse", "");
			newID="expanse";
			$(item).html("-");
		}else{
			realID = idOld.replace("expanse", "");
			newID="collapse";
			$(item).html("+");
		}
		var idd = realID;
		$("#" + idd).slideToggle(500);
		$(item).attr("id", newID+realID);
}

//function getDataFromAjax(item){
//	var id=item.id;
//	if(id.match("acourse")){
//		var idCourse=id.replace("acourse","");
//		var newId="divCourse"+idCourse;
//		if($("#"+newId).html()==undefined){
//			var conte=$("#context").attr("value");
//			var ajax=sendAJAXmessage(conte+"/ajax/course/course_details", "GET", "idCourse", idCourse);
//			ajax.done(function(data){
////				alert(data);
//				var newDiv="<div id='"+newId+"'></div>";
//				if($("#"+newId).html()==undefined)
//					$(newDiv).insertAfter($(item));
//				$("#"+newId).html(data);
//			});
//		}else{
//			$("#"+newId).slideToggle(1000);
//		}
//	}
//}
/**
 * <span class="span_expandible" id="collapseIDTAGTOCOLLAPSE">+</span>	
 */
//function initCollapsable(){
//	$("span[id^='collapse'],span[id^='expanse']").each(function() {
//		$(this).bind("click", function() {
//			var idOld = this.id;
////			alert(idOld+"");
//			var realID;
//			var newID;
//			if(idOld.match("collapse")){
//				realID = idOld.replace("collapse", "");
//				newID="expanse";
//				$(this).html("-");
//			}else{
//				realID = idOld.replace("expanse", "");
//				newID="collapse";
//				$(this).html("+");
//			}
//			var idd = realID;
//			$("#" + idd).slideToggle(500);
//			$(this).attr("id", newID+realID);
//		});
//	});
//}

/**
 * <div> div da rendere draggable
 * <div>
 * <span class="lock-draggable-close" id="draggableIDTODRAG">lock/unlock</span>
 * </div>
 * </div>
 */
function initDraggable(){
	$("span[class^='lock']").bind("click",function(){
		if($(this).hasClass("lock-draggable-close")){
			$(this).removeClass("lock-draggable-close");
			$(this).addClass("lock-draggable-open");
			var idOld = this.id;
			var idTAG=idOld.replace("draggable","");
			$("#"+idTAG).addClass("draggable");
			$("#"+idTAG).draggable();
//			{
//			stop: function( event, ui ) {
//			changePersonalization(this,ui);
//			},
//			});
		}else{
			$(this).removeClass("lock-draggable-open");
			$(this).addClass("lock-draggable-close");
			var idOld = this.id;
			var idTAG=idOld.replace("draggable","");
			var tag=$("#"+idTAG);
			tag.removeClass("draggable");
			tag.draggable( "destroy" );
			changePersonalization(tag);
		}
	});
}


/*function alingDashBoard(){
	var count = $("#nav > li").length;
	var percent=100/count-1;
	$("#nav > li").css({"width":percent+"%"});
	$("#nav > li").css({"text-align":"center"});
}*/

function alingDashBoard(){
	var count = $(".dashboard li").length;
	var percent=100/count;
	$(".dashboard li").css({"width":percent+"%"});
	$(".dashboard li").css({"text-align":"center"});
}

function alingMenu(){
	var count = $(".dashboard li").length;
	var percent=100/count;
	$(".dashboard li").css({"width":percent+"%"});
	$(".dashboard li").css({"text-align":"center"});
}

function selectDashBoard(item){
	$(".dashboard > li.selected_dash_board").attr("class","");
	selectingFromDashBoard(item);
	var url=item.id.replace("Button","");
	window.location=$("#context").attr("value")+"/"+url;
}

function selectingFromDashBoard(element){
	element.parentNode.setAttribute("class","selected_dash_board");
}



function changePersonalization(item){
	//tramite ajax una richiesta che setta il div nella posizione lasciata
	var elementId=item.attr("id");
	var conte=$("#context").attr("value");
	conte=conte+"/personalizzation";
//	var formData = new FormData("caxx=cia&asd=ty");
	//idTAG:name=cicio%surname=pasticcio$idTAG:id=125
//	var dataSend=elementId+":[left#"+item.css("left")+"%top#"+item.css("top")+"]";
	//idTAG:name=cicio%surname=pasticcio$idTAG:id=125
	var dataSend=elementId+"[left:"+item.css("left")+",top:"+item.css("top");
//	var ajax=
	sendAJAXmessage(conte,"POST", "data", dataSend);
//	ajax.done(function(data){
//	alert(data+"");
//	});
}
////formData.append('cazz', "cio");
////formData=formData.serialize();
//var ing=$.ajax({
////beforeSend: function( xhr ) {
////xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
////},
//type: "POST",
//url: conte+"/personalizzation",
////data: { 'name': "John", 'location': "Boston" }, sbagliato
//data:"data="+dataSend,
////contentType:"text; charset=utf-8",
////dataType:"text",
//processData:false
//});
////var ing=$.ajax({
////type: "POST",
////url: conte+"/personalizzation",
////processData: false,
////data: JSON.stringify(dataSend),
////contentType: "application/json",
////dataType:"json"
////});

//$(".processing").css("display","block");

//ing.done(function(msg){
//$(".processing").css("display","none");
//});
function sendAJAXmessage(url,type,name,value){
	var ing=$.ajax({
		type: type,
		url: url,
		data:name+"="+value,
		processData:false
	});
	$(".processing").css("display","block");
	ing.done(function(msg){
		$(".processing").css("display","none");
	});
	return ing;
}

function changeNote(item,idCourse){
	var idd=item.id;
//	alert($("#"+idd).children().html());
	var conte=$("#context").attr("value");
	conte=conte+"/changeNote";
	var dataSend=$("#"+idd).children().html();
	sendAJAXmessage(conte,"POST", "data"+idCourse, dataSend);
}


//removed trash
//$(window).bind("resize", resizeWindow);
//function resizeWindow(e) {
////alineamentoContainer();
//}
//function alineamentoContainer() {
//{
//alert("no");
//$(".container-left").css({"height":"auto"});
//$(".container-center").css({"height":"auto"});
////$(".container-right").css({"height":"auto"});
//$(".container-up").css({"height":"auto"});
//$(".container-down").css({"height":"auto"});

//var left = $(".container-left").css("height");
//var center = $(".container-center").css("height");
////var right = $(".container-right").css("height");

////alert("left : "+left+" center : "+center+" right: "+right);

//((left!=undefined)?left =parseInt(left.replace("px","")):left=0);
//((center!=undefined)?center =parseInt(center.replace("px","")):center=0);
////((right!=undefined)?right =parseInt(right.replace("px","")):right=0);

//if (left >= center) {
//$(".container-center").css({
//"height" : left
//});
//justContainer(left);
//} else if (center >= left) {
//$(".container-left").css({
//"height" : center
//});
//justContainer(center);
//}
//}
////container size
//}

//function justContainer(incr){
//var container=$("container").css("height");
//if(isNaN(container))
//container=0;
//else
//container=container.replace("px","");
//container=parseInt(container, 0);
//container=container+incr;
//incr=$(".container-up").css("height");
//((incr!=undefined)?incr =parseInt(incr.replace("px","")):incr=0);
//incr=parseInt(incr, 0);
//container=container+incr;
//incr=$(".container-down").css("height");
//((incr!=undefined)?incr =parseInt(incr.replace("px","")):incr=0);
//incr=parseInt(incr, 0);
//container=container+incr;
//container=container+20;
//$(".container").css({"height":container+"px"});
//}
