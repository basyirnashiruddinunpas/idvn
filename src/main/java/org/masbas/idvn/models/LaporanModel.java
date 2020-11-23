package org.masbas.idvn.models;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class LaporanModel {
	
	@Id
	private String id;
	
	private String overview;
	private String productAffected;
	private String description;
	private String impact;
	private List<WorkaroundModel> workarounds;
	private List<StatusVendorModel> statusVendor;
	private UserModel vendor;	
	private String references;
	private String vectorString;
	private String status;
	private UserModel createdBy;
	private UserModel editedBy;
	private Date createdTimeStamp;
	private Date updatedTimeStamp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getProductAffected() {
		return productAffected;
	}
	public void setProductAffected(String productAffected) {
		this.productAffected = productAffected;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public List<WorkaroundModel> getWorkarounds() {
		return workarounds;
	}
	public void setWorkarounds(List<WorkaroundModel> workarounds) {
		this.workarounds = workarounds;
	}
	public List<StatusVendorModel> getStatusVendor() {
		return statusVendor;
	}
	public void setStatusVendor(List<StatusVendorModel> statusVendor) {
		this.statusVendor = statusVendor;
	}
	public UserModel getVendor() {
		return vendor;
	}
	public void setVendor(UserModel vendor) {
		this.vendor = vendor;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getVectorString() {
		return vectorString;
	}
	public void setVectorString(String vectorString) {
		this.vectorString = vectorString;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserModel getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserModel createdBy) {
		this.createdBy = createdBy;
	}
	public UserModel getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(UserModel editedBy) {
		this.editedBy = editedBy;
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
	
	

}
