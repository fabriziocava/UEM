package it.unical.uniexam.mvc.controll;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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

		//		Date date = new Date();
		//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//		
		//		String formattedDate = dateFormat.format(date);
		//		
		//		StudentDao s=new StudentDaoImpl();
		//		
		//		s.saveStudent("uigi",new Address(), "125222");
		//		
		//		ArrayList<Student>list =(ArrayList<Student>) s.listStudent();
		////		ArrayList<String> a=new ArrayList<String>();
		////		a.add("how");a.add("are "); a.add("you");a.add("?");
		//		
		//		model.addAttribute("serverTime", formattedDate );
		//		model.addAttribute("students",list);

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest request,Model model){
		model.addAttribute("user",username);
		if(username.equals("s")){
			return "student/home_student";
		}else if(username.equals("p")){
			HttpSession session = request.getSession(true);
			session.setAttribute("user", username);
			return "professor/home";
		}else if(username.equals("m")){
			return "manager/home_manager";
		}
		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model){
		try{
			request.getSession(false).invalidate();
		}catch (Exception e){
			System.err.println("Errore sul logout");
		}
		return "home";
	}
}
