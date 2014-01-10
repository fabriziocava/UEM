$(document).ready(function() {
//	$(".draggable").children(".box-header").each(function(){
//	alert(""+$(this).attr("class"));
//	$(this).draggable({
//	stop: function( event, ui ) {
//	changePersonalization(this,ui);
//	}
//	});
//	});
	$(".draggable").children(".box-header").draggable({
		stop: function( event, ui ) {
			changePersonalization(this,ui);
//			$(this).parent().draggable( "destroy" );
		},
		drag: function(event,ui){
			$(this).parent().css({
			      "left": ui.position.left,
			      "top": ui.position.top
			    });
		}
	});

	alineamentoContainer();
	alingDashBoard();
});

$(window).bind("resize", resizeWindow);
function resizeWindow(e) {
	alineamentoContainer();
}
$(document).ready(function() {
//	$( ".draggable" ).draggable(
});

$(document).ready(function() {
	$("span[id^='collapse'],span[id^='expanse']").each(function() {
		$(this).bind("click", function() {
			var idOld = this.id;
			var realID;
			var newID;
			if(idOld.match("collapse")){
				realID = idOld.replace("collapse", "");
				newID="expanse";
				$(this).html("-");
			}else{
				realID = idOld.replace("expanse", "");
				newID="collapse";
				$(this).html("+");
			}
			var idd = realID;
			$("#" + idd).toggle();
			$(this).attr("id", newID+realID);
		});
	});
});
function alineamentoContainer() {
	{
		$(".container-left").css({"height":"auto"});
		$(".container-center").css({"height":"auto"});
//		$(".container-right").css({"height":"auto"});
		$(".container-up").css({"height":"auto"});
		$(".container-down").css({"height":"auto"});

		var left = $(".container-left").css("height");
		var center = $(".container-center").css("height");
//		var right = $(".container-right").css("height");

//		alert("left : "+left+" center : "+center+" right: "+right);

		((left!=undefined)?left =parseInt(left.replace("px","")):left=0);
		((center!=undefined)?center =parseInt(center.replace("px","")):center=0);
//		((right!=undefined)?right =parseInt(right.replace("px","")):right=0);

		if (left >= center) {
			$(".container-center").css({
				"height" : left
			});
			justContainer(left);
		} else if (center >= left) {
			$(".container-left").css({
				"height" : center
			});
			justContainer(center);
		}
	}
	//container size
}

function justContainer(incr){
	var container=$("container").css("height");
	if(isNaN(container))
		container=0;
	else
		container=container.replace("px","");
	container=parseInt(container, 0);
	container=container+incr;
	incr=$(".container-up").css("height");
	((incr!=undefined)?incr =parseInt(incr.replace("px","")):incr=0);
	incr=parseInt(incr, 0);
	container=container+incr;
	incr=$(".container-down").css("height");
	((incr!=undefined)?incr =parseInt(incr.replace("px","")):incr=0);
	incr=parseInt(incr, 0);
	container=container+incr;
	container=container+20;
	$(".container").css({"height":container+"px"});
}

function alingDashBoard(){
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

function changePersonalization(item,ui){
	//tramite ajax una richiesta che setta il div nella posizione lasciata
	var conte=$("#context").attr("value");
	var dataSend=item.id+"&left="+ui.position.left+"&top="+ui.position.top;
	var ing=$.ajax({
		type: "GET",
		url: conte+"/personalizzation?"+dataSend,
		processData: false
	});

	$("#"+item.id).addClass("processing");

	ing.done(function(){
		$("#"+item.id).removeClass("processing");
	});
}

