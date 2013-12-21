package it.unical.inf.uem.validators;

import java.util.regex.Pattern;

import it.unical.inf.uem.mvc.controller.HomeController;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return HomeController.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "campo", "required","messaggio d'errore");
		
		HomeController c=(HomeController)target;
		if(!Pattern.matches("qualche regola", c.toString()))//invece di c.toString() il campo da validare
			errors.rejectValue("campo", "notValid","messaggio di errore");
			
		//Se ci fosse una classe da validare in questo oggetto
		// possiamo chiamarla in questo modo ---cmq vedi spring-tutorial page 40
		errors.pushNestedPath("campoAltraClasse");
		//commento perchè da errore ovviamente
		//ValidationUtils.invokeValidator(new ClasseValidator(), c.getCampoAltraClasse, errors);
		// la cosa generebbe una cascata di validazioni nel caso in cui ci siano
		// altre classi nelle classi da dover validare
		errors.popNestedPath();
			
	}

}
