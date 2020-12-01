package org.masbas.idvn.helpers.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ModelValidator<T> {
	private ValidatorFactory vfac;
	private Validator validator;
	private T model;
	
	public ModelValidator(T model) {
		vfac = Validation.buildDefaultValidatorFactory();
		validator = vfac.getValidator();
		this.model = model;
	}
	
	public String validate() {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);
		if (constraintViolations.size() > 0) {
			String violationStr = "";
            for (ConstraintViolation<T> violation : constraintViolations) {
                violationStr += violation.getMessage() + "<br>";
            }
            return violationStr;
        }
		return null;
	}

}
