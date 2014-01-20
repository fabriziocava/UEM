package it.unical.uniexam.mvc.controll.secretary;

import it.unical.uniexam.mvc.service.SecretaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secretary/ajax")
public class SecretaryAjaxController {

	@Autowired
	SecretaryService secretaryService;
	
	
	
}
