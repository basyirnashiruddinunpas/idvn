package org.masbas.idvn.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTipeUser() {
		return tipeUser;
	}
	public void setTipeUser(String tipeUser) {
		this.tipeUser = tipeUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<User> getAuditor() {
		return auditor;
	}
	public void setAuditor(List<User> auditor) {
		this.auditor = auditor;
	}
	public List<String> getInsidentHandler() {
		return insidentHandler;
	}
	public void setInsidentHandler(List<String> insidentHandler) {
		this.insidentHandler = insidentHandler;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	
}
