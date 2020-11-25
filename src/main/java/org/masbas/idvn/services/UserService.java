package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.LaporanDao;
import org.masbas.idvn.models.UserDao;
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
	public UserDao registerNewUserAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
		if(emailExist(registrationDto.getEmail())) {
			throw new UserAlreadyExistException("Email sudah digunakan, " + registrationDto.getEmail());
		}
		UserDao user = new UserDao();
		user.setName(registrationDto.getName());
		user.setEmail(registrationDto.getEmail());
		user.setContact(registrationDto.getContact());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setStatus(UserHelper.STATUS_ACTIVE);
		user.setCreatedTimeStamp(new Date());
		user.setUpdatedTimeStamp(new Date());
		user.setTipeUser(registrationDto.getTipeUser());
		
		if(UserHelper.TIPE_NOTIFIER.equals(registrationDto.getTipeUser())) {
			user.setReputation(0);
			user.setRoles(Arrays.asList("ROLE_NOTIFIER"));
		}
		else if(UserHelper.TIPE_AUDITOR.equals(registrationDto.getTipeUser()))
			user.setWebsite(registrationDto.getWebsite());
		
		return userRepository.save(user);
	}
	
	@Override
	public UserDao loginUser(String email, String password) {
		return null;
	}
	
    private boolean emailExist(String email) {    	
        return userRepository.findByEmail(email) != null;
    }

	@Override
	public UserDao getUserById(String id) {
		return userRepository.findByIdUser(id);
	}
	
	@Override
	public UserDao getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<UserDao> findAllVendor() {
		return userRepository.findAllVendor();
	}
	
	public void setAuditor(String idVendor, String idAuditor) {
		UserDao vendor = userRepository.findById(idVendor).get();
		UserDao auditor = userRepository.findById(idAuditor).get();
		List<UserDao> listVendor = vendor.getAuditor();
		if(listVendor == null) {
			listVendor = new ArrayList<UserDao>();
		}
		listVendor.add(auditor);
		vendor.setAuditor(listVendor);
		userRepository.save(vendor);
	}
	
}
