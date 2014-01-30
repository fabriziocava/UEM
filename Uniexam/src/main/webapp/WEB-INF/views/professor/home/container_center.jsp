<%@page import="it.unical.uniexam.hibernate.domain.utility.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unical.uniexam.hibernate.domain.utility.EventsCalendar"%>
<div class="container-center">

<link href="${pageContext.request.contextPath}/res/js/fullcalendar/fullcalendar.css" rel="stylesheet">
<!-- <script src="../lib/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath}/res/js/lib/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/fullcalendar/fullcalendar.min.js"></script>
<script>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		var calendar = $('#calendar').fullCalendar({
			header: {
				left: 'prev,next,today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			aspectRatio: 3,
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
				alert(title+" "+start+" "+end+" "+allDay);
				var url=prompt("inserisci un url");
				alert(url);
				FormData data=new FormData();
				data.append('title',title);
				data.append('start',start);
				data.append('end',end);
				data.append('allDay',allDay);
				data.append('','');
// 				sendAJAXmessage2('/calendar/save', 'POST', data);
			},
			editable: true,
			events: [
			        <%ArrayList<Event> eventsCalendar=(ArrayList<Event>)request.getAttribute("events");
			        if(eventsCalendar!=null && eventsCalendar.size()>0){%>
			        <%for(Event event:eventsCalendar){%>
			        {
			        	title: '<%=event.title%>',
			        	start: '<%=event.startDate%>',
			        	end: '<%=event.endDate%>',
			        	allDay: '<%=event.allDay%>',
			        	url: '<%=event.url%>',
			        },
			        <%}%>
			        <%}%>
			        {
			        	
			        }
			        ]
			,
			        eventClick: function(event) {
			            if (event.url) {
			                window.open(event.url);
			                return false;
			            }
			        },
			        eventResize: function(event,dayDelta,minuteDelta,revertFunc) {

			            alert(
			                "The end date of " + event.title + "has been moved " +
			                dayDelta + " days and " +
			                minuteDelta + " minutes."
			            );

			            if (!confirm("is this okay?")) {
			                revertFunc();
			            }

			        },
			        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {

			            alert(
			                event.title + " was moved " +
			                dayDelta + " days and " +
			                minuteDelta + " minutes."
			            );

			            if (allDay) {
			                alert("Event is now all-day");
			            }else{
			                alert("Event has a time-of-day");
			            }

			            if (!confirm("Are you sure about this change?")) {
			                revertFunc();
			            }

			        }
			
		});
// 		 $('#calendar').fullCalendar('today');
		
	});

</script>

<div id="calendar">

</div>

</div>


<!-- events: [ -->
<!-- 				{ -->
<!-- 					title: 'All Day Event', -->
<!-- 					start: new Date(y, m, 1) -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					title: 'Long Event', -->
<!-- 					start: new Date(y, m, d-5), -->
<!-- 					end: new Date(y, m, d-2) -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					id: 999, -->
<!-- 					title: 'Repeating Event', -->
<!-- 					start: new Date(y, m, d-3, 16, 0), -->
<!-- 					allDay: false -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					id: 999, -->
<!-- 					title: 'Repeating Event', -->
<!-- 					start: new Date(y, m, d+4, 16, 0), -->
<!-- 					allDay: false -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					title: 'Meeting', -->
<!-- 					start: new Date(y, m, d, 10, 30), -->
<!-- 					allDay: false -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					title: 'Lunch', -->
<!-- 					start: new Date(y, m, d, 12, 0), -->
<!-- 					end: new Date(y, m, d, 14, 0), -->
<!-- 					allDay: false -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					title: 'Birthday Party', -->
<!-- 					start: new Date(y, m, d+1, 19, 0), -->
<!-- 					end: new Date(y, m, d+1, 22, 30), -->
<!-- 					allDay: false -->
<!-- 				}, -->
<!-- 				{ -->
<!-- 					title: 'Click for Google', -->
<!-- 					start: new Date(y, m, 28), -->
<!-- 					end: new Date(y, m, 29), -->
<!-- 					url: 'http://google.com/' -->
<!-- 				} -->
<!-- 			] -->