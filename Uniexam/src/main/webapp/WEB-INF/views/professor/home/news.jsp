
<%@page import="it.unical.uniexam.mvc.controll.professor.News"%>
<%@page import="java.util.ArrayList"%>
<% %>

<%ArrayList<News>newss=(ArrayList<News>)request.getAttribute("newss"); %>
<div class="aligncenter">
	<div id="NewsDiv" class="startHide otherNewsMok">
		<ul id="newsmok">
		<%if(newss!=null && newss.size()>0){ %>
		<%for(News n:newss){ %>
			<li><a href="<%=n.getLink()%>"><%=n.getMessage() %></a></li>
		<% }%>
		<%}else{ %>
		<li><a href="#">No new news</a></li>
		<%} %>
		</ul>
	</div>
	<span class="span_expandible" style="float: left; position: relative; left: 25px; top: 2px;"
		onclick="collapseMok(this);clickOneCollapse(this,'NewsDiv');"  id="collapseNewsDiv">+</span>
	<ul id="news" class="js-hidden">
		<%if(newss!=null && newss.size()>0){ %>
		<%for(News n:newss){ %>
			<li><a href="<%=n.getLink()%>"><%=n.getMessage() %></a></li>
		<% }%>
		<%}else{ %>
		<li><a href="#">No new news</a></li>
		<%} %>
	</ul>
</div>








<script type="text/javascript">
	//     $(function () {
	//         $('#news').ticker();
	//     });
	function clickOneCollapse(item,item2){
 			if($(item).html()=="-" ){
				$("div[id^='ticker-wrapper-']").css('border-bottom-left-radius','0px');
				$("div[id^='ticker-wrapper-']").css('border-bottom-right-radius','0px');
				var xy=$(item).offset();
				$("#"+item2).css('left',xy['left']-10);
				$("#"+item2).css('top',xy['top']+20);
			}else{
				$("div[id^='ticker-wrapper-']").css('border-bottom-left-radius','15px');
				$("div[id^='ticker-wrapper-']").css('border-bottom-right-radius','15px');
			}
}
	$(function() {
		// start the ticker 
		//	$('#js-news').ticker();

// 		clickOneCollapse('collapseNewsDiv','NewsDiv');
		
		// hide the release history when the page loads
		$('#release-wrapper').css('margin-top',
				'-' + ($('#release-wrapper').height() + 20) + 'px');

		// show/hide the release history on click
		$('a[href="#release-history"]').toggle(function() {
			$('#release-wrapper').animate({
				marginTop : '0px'
			}, 600, 'linear');
		}, function() {
			$('#release-wrapper').animate({
				marginTop : '-' + ($('#release-wrapper').height() + 20) + 'px'
			}, 600, 'linear');
		});

		//	$('#download a').mousedown(function () {
		//		_gaq.push(['_trackEvent', 'download-button', 'clicked'])		
		//	});
	});
	$(function() {
		$('#news').ticker({
			speed : 01,
			//             ajaxFeed: false, 
			//             feedUrl: false,  

			//             feedType: 'xml', 
			//             htmlFeed: true,  
			//             debugMode: true,
			controls : true,
			titleText : 'Latest',
			displayType : 'reveal',
			direction : 'ltr',
			pauseOnItems : 2000,
			fadeInSpeed : 600,
			fadeOutSpeed : 2000
		});
	});
	//     $(function () {
	//         $('#js-news').ticker({
	//             speed: 0.10,
	//             htmlFeed: false,
	//             fadeInSpeed: 600,
	//             titleText: 'Latest News'
	//         });
	//     });
	// $(function () {
	//         $('#js-news').ticker(
	//             speed: 0.10,           // The speed of the reveal
	//             ajaxFeed: false,       // Populate jQuery News Ticker via a feed
	//             feedUrl: false,        // The URL of the feed
	//     	                       // MUST BE ON THE SAME DOMAIN AS THE TICKER
	//             feedType: 'xml',       // Currently only XML
	//             htmlFeed: true,        // Populate jQuery News Ticker via HTML
	//             debugMode: true,       // Show some helpful errors in the console or as alerts
	//       	                       // SHOULD BE SET TO FALSE FOR PRODUCTION SITES!
	//             controls: true,        // Whether or not to show the jQuery News Ticker controls
	//             titleText: 'Latest',   // To remove the title set this to an empty String
	//             displayType: 'reveal', // Animation type - current options are 'reveal' or 'fade'
	//             direction: 'ltr',       // Ticker direction - current options are 'ltr' or 'rtl'
	//             pauseOnItems: 2000,    // The pause on a news item before being replaced
	//             fadeInSpeed: 600,      // Speed of fade in animation
	//             fadeOutSpeed: 300      // Speed of fade out animation
	//         );
	//     });
</script>

<style>
.otherNewsMok{
background-color: rgb(226, 209, 209);
width: 821px;
position: fixed;
left: 135px;
top: 122px;
border-bottom-right-radius: 15px;
border-bottom-left-radius: 15px;
}
/* Ticker Styling */
.ticker-wrapper.has-js {
	margin: 1px 0px 0px 0px;
	padding: 0px 20px;
	width: 780px;
	height: 32px;
	display: block;
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px;
	border-radius: 15px;
	background-color: #f8f0db;
	font-size: 0.75em;
}

.ticker {
	width: 710px;
	height: 23px;
	display: block;
	position: relative;
	overflow: hidden;
	background-color: #f8f0db;
}

.ticker-title {
	padding-top: 9px;
	color: #990000;
	font-weight: bold;
	background-color: #f8f0db;
	text-transform: uppercase;
}

.ticker-content {
	margin: 0px;
	padding-top: 9px;
	position: absolute;
	color: #1F527B;
	font-weight: bold;
	background-color: #f8f0db;
	overflow: hidden;
	white-space: nowrap;
	line-height: 1.2em;
}

.ticker-content:focus {none;
	
}

.ticker-content a {
	text-decoration: none;
	color: #1F527B;
}

.ticker-content a:hover {
	text-decoration: underline;
	color: #0D3059;
}

.ticker-swipe {
	padding-top: 9px;
	position: absolute;
	top: 0px;
	background-color: #f8f0db;
	display: block;
	width: 800px;
	height: 23px;
}

.ticker-swipe span {
	margin-left: 1px;
	background-color: #f8f0db;
	border-bottom: 1px solid #1F527B;
	height: 12px;
	width: 7px;
	display: block;
}

.ticker-controls {
	/* 	padding: 8px 0px 0px 0px; */
	/* 	list-style-type: none; */
	/* 	float: left; */
	position: relative;
	float: right;
	padding: 0px;
	margin: 0px;
	top: -14px;
}

.ticker-controls li {
	padding: 0px;
	margin-left: 5px;
	float: left;
	cursor: pointer;
	height: 16px;
	width: 16px;
	display: block;
}

.ticker-controls li.jnt-play-pause {
	background-image:
		url("${pageContext.request.contextPath}/res/js/ticker/images/controls.png");
	background-position: 32px 16px;
}

.ticker-controls li.jnt-play-pause.over {
	background-position: 32px 32px;
}

.ticker-controls li.jnt-play-pause.down {
	background-position: 32px 0px;
}

.ticker-controls li.jnt-play-pause.paused {
	background-image:
		url('${pageContext.request.contextPath}/res/js/ticker/images/controls.png');
	background-position: 48px 16px;
}

.ticker-controls li.jnt-play-pause.paused.over {
	background-position: 48px 32px;
}

.ticker-controls li.jnt-play-pause.paused.down {
	background-position: 48px 0px;
}

.ticker-controls li.jnt-prev {
	background-image:
		url("${pageContext.request.contextPath}/res/js/ticker//images/controls.png");
	background-position: 0px 16px;
}

.ticker-controls li.jnt-prev.over {
	background-position: 0px 32px;
}

.ticker-controls li.jnt-prev.down {
	background-position: 0px 0px;
}

.ticker-controls li.jnt-next {
	background-image:
		url('${pageContext.request.contextPath}/res/js/ticker/images/controls.png');
	background-position: 16px 16px;
}

.ticker-controls li.jnt-next.over {
	background-position: 16px 32px;
}

.ticker-controls li.jnt-next.down {
	background-position: 16px 0px;
}

.js-hidden {
	display: none;
}

.no-js-news {
	padding: 10px 0px 0px 45px;
	color: #F8F0DB;
}

.left .ticker-swipe {
	/*left: 80px;*/
	
}

.left .ticker-controls,.left .ticker-content,.left .ticker-title,.left .ticker
	{
	float: left;
}

.left .ticker-controls {
/* 	padding-left: 6px; */
	padding-left: 700px;
}

.right .ticker-swipe {
	/*right: 80px;*/
	
}

.right .ticker-controls,.right .ticker-content,.right .ticker-title,.right .ticker
	{
	float: right;
}

.right .ticker-controls {
	padding-right: 6px;
}
</style>
