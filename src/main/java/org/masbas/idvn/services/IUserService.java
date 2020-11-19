package org.masbas.idvn.services;

import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.viewmodels.RegistrationDto;

public interface IUserService {
	UserModel registerNewUserAccount(RegistrationDto userDto)
		throws UserAlreadyExistException;
}
