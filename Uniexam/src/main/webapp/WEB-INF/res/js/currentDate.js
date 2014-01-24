var today=new Date();
var monthNumber=today.getMonth()+1;
var day = (today.getDate() < 10) ? "0"+today.getDate() : today.getDate();
var month = (today.getMonth() < 9) ? "0"+monthNumber : monthNumber;
var currentDate=day+"/"+month+"/"+today.getFullYear(); 
var calendar=document.createElement("input");
calendar.setAttribute("type", "text");
calendar.setAttribute("name", "date");
calendar.setAttribute("class", "tcal");
calendar.setAttribute("value", currentDate);
document.getElementById("date").appendChild(calendar);
document.getElementById("date1").appendChild(calendar);

//('<input type=text name=date class=tcal value='+currentDate+' />');

