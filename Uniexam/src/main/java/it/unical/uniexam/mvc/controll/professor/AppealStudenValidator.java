package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.hibernate.domain.AppealStudent;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AppealStudenValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AppealStudent.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AppealStudent appealStudent =(AppealStudent)target;
		
		if(appealStudent.getAppeal()==null && appealStudent.getCourse() == null) {
	          errors.rejectValue("appeal","error.code", "Appeal or Course must be not null");
	          errors.rejectValue("course","error.code","Appeal or Course must be not null");
	    }
		if(appealStudent.getStudent()==null) {
	          errors.rejectValue("student","error.code", "Student must be not null");
	    }
		if(appealStudent.getTemporany_vote()==null 
				|| !(appealStudent.getTemporany_vote()>=18 && appealStudent.getTemporany_vote()<=31)) {
			errors.rejectValue("temporany_vote", "message.professor.vote.error");
	    }
	}

}
