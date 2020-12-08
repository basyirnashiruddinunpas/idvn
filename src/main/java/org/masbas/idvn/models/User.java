package org.masbas.idvn.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")
public class User {
	
	@Id
	private String id;
	
	private String name;
	private String email;
	private String password;
	private String contact;
	private String tipeUser;
	private List<String> roles;
	private String status;
	private int reputation;
	private String website;
	
	@DBRef
	private List<User> auditor;
	private List<String> insidentHandler;
	
	private Date createdTimeStamp;
	private Date updatedTimeStamp;
	
}
