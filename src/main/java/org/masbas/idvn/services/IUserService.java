package org.masbas.idvn.services;

import java.util.List;

import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserDao;
import org.masbas.idvn.viewmodels.RegistrationDto;

public interface IUserService {
	
	UserDao registerNewUserAccount(RegistrationDto userDto)
		throws UserAlreadyExistException;
	
	public UserDao loginUser(String email, String password);
	
	public UserDao getUserById(String id);
	
	public UserDao getUserByEmail(String email);
	
	public List<UserDao> findAllVendor();
	
}
