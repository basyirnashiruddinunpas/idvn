package org.masbas.idvn.helpers.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.masbas.idvn.viewmodels.RegistrationDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 
  
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      RegistrationDto registration = (RegistrationDto) obj;
      return registration.getPassword().equals(registration.getMatchingPassword());    
  }     
}