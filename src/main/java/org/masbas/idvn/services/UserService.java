package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.repositories.UserRepository;
import org.masbas.idvn.viewmodels.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public UserModel registerNewUserAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
		if(emailExist(registrationDto.getEmail())) {
			throw new UserAlreadyExistException("Email sudah digunakan, " + registrationDto.getEmail());
		}
		UserModel user = new UserModel();
		user.setName(registrationDto.getName());
		user.setEmail(registrationDto.getEmail());
		user.setContact(registrationDto.getContact());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setStatus(UserHelper.STATUS_ACTIVE);
		user.setCreatedTimeStamp(new Date());
		user.setUpdatedTimeStamp(new Date());
		
		if(UserHelper.TIPE_NOTIFIER.equals(registrationDto.getTipeUser())) {
			user.setReputation(0);
			user.setRoles(Arrays.asList("ROLE_NOTIFIER"));
		}
		else if(UserHelper.TIPE_AUDITOR.equals(registrationDto.getTipeUser()))
			user.setWebsite(registrationDto.getWebsite());
		
		return userRepository.save(user);
	}
	
	public UserModel loginUser(String email, String password) {
		return null;
	}
	
	
    private boolean emailExist(String email) {    	
        return userRepository.findByEmail(email) != null;
    }


	@Override
	public UserModel getUserById(String id) {
		return userRepository.findByIdUser(id);
	}
	
	public UserModel getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
