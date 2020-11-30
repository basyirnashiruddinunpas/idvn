package org.masbas.idvn.viewmodels;

import java.util.Date;
import java.util.List;

import org.masbas.idvn.models.User;

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
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
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}
	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
	
	

}
