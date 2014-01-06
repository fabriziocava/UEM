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
		
		left =parseInt(left.replace("px",""));
		center =parseInt(center.replace("px",""));
		right =parseInt(right.replace("px",""));
		
//		alert("left : "+left+" center : "+center+" right: "+right);
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
	incr=incr.replace("px","");
	incr=parseInt(incr, 0);
	container=container+incr;
	incr=$(".container-down").css("height");
	incr=incr.replace("px","");
	incr=parseInt(incr, 0);
	container=container+incr;
	container=container+20;
	$(".container").css({"height":container+"px"});

//	$(".container-left").css({"width":"auto"});
//	$(".container-center").css({"width":"auto"});
//	$(".container-right").css({"width":"auto"});
}

function alingDashBoard(){
	var count = $(".dashboard li").length;
	var percent=100/count;
//	percent=percent-1;
	$(".dashboard li").css({"width":percent+"%"});
	$(".dashboard li").css({"text-align":"center"});
//	$(".dashboard li a")[0].style.margin="0px 0px 0px 0px";// attr("width",percent+"%");
//	if(percent%2==1 && count >1){
//		percent=percent+1;
//		$(".dashboard li")[0].style.width=percent+"%";// attr("width",percent+"%");
//		$(".dashboard li")[count-1].style.width=percent+"%"; //.attr("width",percent+"%");
//	}
//	$(".dashboard li").css({"margin":"3px"});
}	

function selectDashBoard(item){
	$(".dashboard > li.selected").attr("class","");
	$(".dashboard > li > a").each(function(index,element){
		var d=element.innerHTML;
		if(d==item){
			element.parentNode.setAttribute("class","selected");
		}
	});
}

