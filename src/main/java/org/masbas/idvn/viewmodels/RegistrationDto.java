package org.masbas.idvn.viewmodels;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.masbas.idvn.helpers.validators.PasswordMatches;
import org.masbas.idvn.helpers.validators.ValidEmail;
import org.masbas.idvn.models.UserModel;

@PasswordMatches
public class RegistrationDto {
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;
	
	@NotNull
	@NotEmpty
	private String password;
	
	private String matchingPassword;
	
	@NotNull
	@NotEmpty
	private String contact;
	
	private String tipeUser;
	
	private String status;
	
	private String website;
	
	private int reputation;
	
	private List<UserModel> auditor;
	
	private List<String> insidentHandler;
	
	private ZonedDateTime createdTimeStamp;
	private ZonedDateTime updateTimeStamp;
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
	public List<UserModel> getAuditor() {
		return auditor;
	}
	public void setAuditor(List<UserModel> auditor) {
		this.auditor = auditor;
	}
	public List<String> getInsidentHandler() {
		return insidentHandler;
	}
	public void setInsidentHandler(List<String> insidentHandler) {
		this.insidentHandler = insidentHandler;
	}
	public ZonedDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(ZonedDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public ZonedDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}
	public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
	
	

}
