package it.unical.uniexam.mvc.service;


import it.unical.uniexam.MokException;

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
		Date result =null;
		try{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy kk:mm");
		result =  df.parse(data);
		}catch(Exception e){
			try{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				result =  df.parse(data);
			}catch(Exception e2){
				new MokException(e2);
			}
		}
//		Date d=DateFormat.getInstance().parse(data);
		return new Date(result.getTime());
	}

}
