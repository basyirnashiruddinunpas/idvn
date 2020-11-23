package org.masbas.idvn.services;

import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.viewmodels.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;

public interface IUserService {
	
	UserModel registerNewUserAccount(RegistrationDto userDto)
		throws UserAlreadyExistException;
	
	public UserModel getUserById(String id);
}
