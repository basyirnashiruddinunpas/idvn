package org.masbas.idvn.services;

import java.time.ZonedDateTime;

import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.repositories.UserRepository;
import org.masbas.idvn.viewmodels.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserModel registerNewUserAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
		if(emailExist(registrationDto.getEmail())) {
			throw new UserAlreadyExistException("Email sudah digunakan, " + registrationDto.getEmail());
		}
		
		UserModel user = new UserModel();
		user.setName(registrationDto.getName());
		user.setEmail(registrationDto.getEmail());
		user.setContact(registrationDto.getContact());
		user.setStatus(UserHelper.STATUS_ACTIVE);
		user.setCreatedTimeStamp(ZonedDateTime.now());
		user.setUpdatedTimeStamp(ZonedDateTime.now());
		
		if(UserHelper.TIPE_NOTIFIER.equals(registrationDto.getTipeUser()))
			user.setReputation(0);
		else if(UserHelper.TIPE_AUDITOR.equals(registrationDto.getTipeUser()))
			user.setWebsite(registrationDto.getWebsite());
		
		return userRepository.save(user);
	}
	
    private boolean emailExist(String email) {    	
        return userRepository.findByEmail(email) != null;
    }
	
}
