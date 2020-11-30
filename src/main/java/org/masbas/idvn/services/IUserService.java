package org.masbas.idvn.services;

import java.util.List;

import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.User;
import org.masbas.idvn.viewmodels.RegistrationVM;

public interface IUserService {
	
	User registerNewUserAccount(RegistrationVM userDto)
		throws UserAlreadyExistException;
	
	public User loginUser(String email, String password);
	
	public User getUserById(String id);
	
	public User getUserByEmail(String email);
	
	public List<User> findAllVendor();
	
}
