package it.unical.uniexam.mvc.controll;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.HomeService;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;
import it.unical.uniexam.mvc.service.impl.HomeServiceImpl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.transaction.synchronization.HibernateSynchronizationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class LoginController {

	@Autowired
	HomeService homeService;
	//	@Autowired
	//	MainService mainService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
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

		return UtilsService.LOGIN;
	}

	// serve un pattern sia per la session
	// serve anche un pattern per la personalizzazione

	@RequestMapping(value=UtilsService.LOGIN, method = RequestMethod.POST)
	public String login(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("password") String password
			, Model model){

		ArrayList<Object>error= homeService.loginUser(email, password);
		User user=null;
		Integer err=(Integer) error.remove(0);
		switch (err) {
		case HomeService.NO_ERROR:
			user=(User)error.get(0);
			break;
		case HomeService.ERROR_PASSWD:
			return UtilsService.redirectToErrorPageGeneral("Password non corretta", "password",model);
		case HomeService.ERROR_USER:
			return UtilsService.redirectToErrorPageGeneral("Username non corretto", "username",model);
		default:
			break;
		}
		HttpSession session = request.getSession();
		homeService.registerSession(session.getId(),user.getId());
		if(user.getType()==User.TYPE.PROFESSOR){
			return "redirect:"+ProfessorService.PROFESSOR_HOME;
		}else if(user.getType()==User.TYPE.STUDENT){
			
		}
		return "";
//		return new ModelAndView("home", "model", "model");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model){
		try{
			HttpSession session = request.getSession(false);
			if(session!=null && !homeService.unRegisterSession(session.getId()))
				return UtilsService.redirectToErrorPageGeneral("Session non chiusa", "session",model);
		}catch (Exception e){
			System.err.println("Errore sul logout");
			new MokException(e);
		}
		return UtilsService.LOGIN;
	}
}
