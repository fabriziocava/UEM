package it.unical.uniexam.mvc.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatter implements Formatter<Date> {

	
	@Override
	public String print(Date data, Locale locale) {
	return data.getTime()+"";
	}

	@Override
	public Date parse(String data, Locale locale) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy kk:mm");
		Date result =  df.parse(data);  
//		Date d=DateFormat.getInstance().parse(data);
		return new Date(result.getTime());
	}

}
