<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.js" type="text/javascript"></script>
<link href="css/student.css" media="all" rel="stylesheet" type="text/css" />
<link href="css/menu.css" media="all" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var timeout         = 500;
	var closetimer		= 0;
	var ddmenuitem      = 0;
	
	function jsddm_open()
	{	jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}
	
	function jsddm_close()
	{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
	
	function jsddm_timer()
	{	closetimer = window.setTimeout(jsddm_close, timeout);}
	
	function jsddm_canceltimer()
	{	if(closetimer)
		{	window.clearTimeout(closetimer);
			closetimer = null;}}
	
	$(document).ready(function()
	{	$('#jsddm > li').bind('mouseover', jsddm_open);
		$('#jsddm > li').bind('mouseout',  jsddm_timer);});
	
	document.onclick = jsddm_close;
</script>

<title>Student ${user}</title>
</head>
<body>
<table>
	<tbody>
		<tr>
			<td>
				<img alt="" src="img/logo.png">
			</td>
			<td>
				<font>Welcome ${user}</font>
				<br>
				<font>INSERIRE DATA</font>
			</td>
		</tr>
	</tbody>
</table>
<br>

<ul id="jsddm">
	<li><a href="#">Home</a></li>
	<li><a href="#">Exams</a>
		<ul>
			<li><a href="#">Sessions</a></li>
			<li><a href="#">Acknowledgment result</a></li>
		</ul>
	</li>
	<li><a href="#">Groups</a></li>
	<li><a href="#">Career</a></li>
	<li><a href="#">Settings</a></li>
	<li><a href="#">Help</a></li>
</ul>

</body>
</html>