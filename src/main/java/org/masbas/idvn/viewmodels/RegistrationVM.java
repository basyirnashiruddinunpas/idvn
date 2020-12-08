package org.masbas.idvn.viewmodels;

import java.util.Date;
import java.util.List;

import org.masbas.idvn.models.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationVM {
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String matchingPassword;
	
	private String contact;
	
	private String tipeUser;
	
	private String status;
	
	private String website;
	
	private int reputation;
	
	private List<User> auditor;
	
	private List<String> insidentHandler;
	
	private Date createdTimeStamp;
	private Date updateTimeStamp;
	

}
