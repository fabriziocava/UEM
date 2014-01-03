package it.unical.uniexam.mvc.controll;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.HomeService;
import it.unical.uniexam.mvc.service.impl.HomeServiceImpl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.transaction.synchronization.HibernateSynchronizationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 * 
 * @author luigi
 *
 */
@Controller
public class HomeController {

	@Autowired
	HomeService homeService;
	//	@Autowired
	//	MainService mainService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		//				StudentDao s=new StudentDaoImpl();
		//				
		//				s.saveStudent("uigi",new Address(), "125222");
		//				
		//				ArrayList<Student>list =(ArrayList<Student>) s.listStudent();
		//		ArrayList<String> a=new ArrayList<String>();
		//		a.add("how");a.add("are "); a.add("you");a.add("?");

		model.addAttribute("serverTime", formattedDate );
		//				model.addAttribute("students",list);

		return "home";
	}

	// serve un pattern sia per la session
	// serve anche un pattern per la personalizzazione

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, ModelAndView model){
		Session session=null;
		ArrayList<Object>error= homeService.loginUser(email, password);

		Integer err=(Integer) error.get(0);
		switch (err) {
		case HomeService.NO_ERROR:
			session=(Session) error.get(1);
			break;
		case HomeService.ERROR_PASSWD:
			return redirectToErrorPagePasswd();
		case HomeService.ERROR_USER:
			return redirectToErrorPageUser();
		default:
			break;
		}
		model.addObject("session", session);
		if(session.getType()==User.TYPE.PROFESSOR){
//			return "redirect:professor/home";
			return new ModelAndView("redirect:professor/home", "model", model);
		}else if(session.getType()==User.TYPE.STUDENT){
//			return "";
			return new ModelAndView("home", "model", "model");
		}
//		return "";
		return new ModelAndView("home", "model", "model");
	}

	private ModelAndView redirectToErrorPageUser() {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView redirectToErrorPagePasswd() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model){
		try{
			request.getSession(false).invalidate();
		}catch (Exception e){
			System.err.println("Errore sul logout");
			new MokException(e);
		}
		return "home";
	}
}
