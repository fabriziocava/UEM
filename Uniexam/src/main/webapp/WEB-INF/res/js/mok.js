function alineamentoContainer() {
	{
		$(".container-left").css({"height":"auto"});
		$(".container-center").css({"height":"auto"});
		$(".container-right").css({"height":"auto"});
		$(".container-up").css({"height":"auto"});
		$(".container-down").css({"height":"auto"});

		var left = $(".container-left").css("height");
		var center = $(".container-center").css("height");
		var right = $(".container-right").css("height");
		
//		alert("left : "+left+" center : "+center+" right: "+right);
		
		((left!=undefined)?left =parseInt(left.replace("px","")):left=0);
		((center!=undefined)?center =parseInt(center.replace("px","")):center=0);
		((right!=undefined)?right =parseInt(right.replace("px","")):right=0);

		if (left >= center && left >= right) {
			$(".container-center").css({
				"height" : left
			});
			$(".container-right").css({
				"height" : left
			});
			justContainer(left);
		} else if (center >= right && center >= left) {
			$(".container-right").css({
				"height" : center
			});
			$(".container-left").css({
				"height" : center
			});
			justContainer(center);
		} else if (right >= center && right >= left) {
			$(".container-left").css({
				"height" : right
			});
			$(".container-center").css({
				"height" : right
			});
			justContainer(right);
		}else{
			justContainer(right);
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
	var count = $("#nav > li").length;
	var percent=100/count-1;
	$("#nav > li").css({"width":percent+"%"});
	$("#nav > li").css({"text-align":"center"});
}	

function alingMenu(){
	var count = $(".dashboard li").length;
	var percent=100/count;
	$(".dashboard li").css({"width":percent+"%"});
	$(".dashboard li").css({"text-align":"center"});
}

function selectDashBoard(item){
	$(".dashboard > li.selected_dash_board").attr("class","");
//	$(".dashboard > li > a").each(function(index,element){
//		var d=element.innerHTML;
//		if(d==item){
			selectingFromDashBoard(item);
//			alert($("#context").attr("value")+"/"+element.id+"");
			var url=item.id.replace("Button","");
			window.location=$("#context").attr("value")+"/"+url;
//		}
//	});
}

function selectingFromDashBoard(element){
	element.parentNode.setAttribute("class","selected_dash_board");
}
