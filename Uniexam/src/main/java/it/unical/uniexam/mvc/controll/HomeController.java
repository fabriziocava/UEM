package it.unical.uniexam.mvc.controll;

import it.unical.uniexam.hibernate.dao.StudentDao;
import it.unical.uniexam.hibernate.dao.impl.StudentDaoImpl;
import it.unical.uniexam.hibernate.domain.Address;
import it.unical.uniexam.hibernate.domain.Student;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		StudentDao s=new StudentDaoImpl();
		
		s.saveStudent("uigi",new Address(), "125222");
		
		ArrayList<Student>list =(ArrayList<Student>) s.listStudent();
//		ArrayList<String> a=new ArrayList<String>();
//		a.add("how");a.add("are "); a.add("you");a.add("?");
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("students",list);
		
		return "home";
	}
	
}
