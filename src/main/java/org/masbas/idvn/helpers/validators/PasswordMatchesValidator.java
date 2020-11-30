package org.masbas.idvn.helpers.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.masbas.idvn.viewmodels.RegistrationVM;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 
  
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      RegistrationVM registration = (RegistrationVM) obj;
      return registration.getPassword().equals(registration.getMatchingPassword());    
  }     
}