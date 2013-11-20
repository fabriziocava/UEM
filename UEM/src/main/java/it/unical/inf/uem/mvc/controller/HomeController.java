package it.unical.inf.uem.mvc.controller;

//import it.unical.inf.uem.hib.dao.CityDao;
//import it.unical.inf.uem.hib.dao.imp.CityDaoImpl;
//import it.unical.inf.uem.hib.domain.City;

import java.text.DateFormat;
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

		model.addAttribute("serverTime", formattedDate );

//		CityDao cityDao=new CityDaoImpl();
//		Long id1=cityDao.saveCity("Acri");
//		Long id2=cityDao.saveCity("Acri2");
//		Long id3=cityDao.saveCity("Acri3");
//		Long id4=cityDao.saveCity("Acri4");
//
//		cityDao.listCities(); ///tutte le citta
//
//		City idc1=cityDao.getCityWithId((long) 1);
//		City idc3=cityDao.getCityWithId((long) 3);
//
//		City idc2=cityDao.getCityWithId((long) 2);
//		City idc4=cityDao.getCityWithId((long) 4);
//
//		cityDao.updateCity(idc4.getId(), "Acri5");
//
//		cityDao.listCities(); // modifica della 4 in 5
//
//		cityDao.deleteCity(idc2.getId());
//
//		cityDao.listCities(); // eliminazione della 2

		return "home";
	}

}
