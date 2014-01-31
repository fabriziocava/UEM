package it.unical.uniexam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public static String getDayMonthYear(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(data);
	}
	
	public static int getDay(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return Integer.parseInt(dateFormat.format(data));
	}
	
	public static int getMonth(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return Integer.parseInt(dateFormat.format(data));
	}
	
	public static int getYear(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return Integer.parseInt(dateFormat.format(data));
	}
	
	public static int getHour(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
		return Integer.parseInt(dateFormat.format(data));
	}
	
	public static int getMinute(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
		return Integer.parseInt(dateFormat.format(data));
	}
		
	public static String getHourString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
		return dateFormat.format(data);
	}
	
	public static String getMinuteString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
		return dateFormat.format(data);
	}

}
