package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.Laporan;
import org.masbas.idvn.models.User;
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
	public User registerNewUserAccount(RegistrationDto registrationDto) throws UserAlreadyExistException {
		if(emailExist(registrationDto.getEmail())) {
			throw new UserAlreadyExistException("Email sudah digunakan, " + registrationDto.getEmail());
		}
		User user = new User();
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
	public User loginUser(String email, String password) {
		return null;
	}
	
    private boolean emailExist(String email) {    	
        return userRepository.findByEmail(email) != null;
    }

	@Override
	public User getUserById(String id) {
		return userRepository.findByIdUser(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> findVendorByAuditor(User user) {
		return userRepository.findVendorByAuditor(new ObjectId(user.getId()));
	}
	
	@Override
	public List<User> findAllVendor() {
		return userRepository.findAllVendor();
	}
	
	public void setAuditor(String idVendor, String idAuditor) {
		User vendor = userRepository.findById(idVendor).get();
		User auditor = userRepository.findById(idAuditor).get();
		List<User> listVendor = vendor.getAuditor();
		if(listVendor == null) {
			listVendor = new ArrayList<User>();
		}
		listVendor.add(auditor);
		vendor.setAuditor(listVendor);
		userRepository.save(vendor);
	}
	
}
