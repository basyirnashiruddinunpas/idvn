package org.masbas.idvn.models;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "laporan")
public class LaporanDao {
	
	@Id
	private String id;
	
	private String code;
	private String overview;
	private String productAffected;
	private String description;
	private String impact;
	private List<WorkaroundDao> workarounds;
	private List<StatusVendorDao> statusVendor;
	
	@DBRef
	private UserDao vendor;	
	private String references;
	private String vectorString;
	private String status;
	@DBRef
	private UserDao createdBy;
	@DBRef
	private UserDao editedBy;
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
	public List<WorkaroundDao> getWorkarounds() {
		return workarounds;
	}
	public void setWorkarounds(List<WorkaroundDao> workarounds) {
		this.workarounds = workarounds;
	}
	public List<StatusVendorDao> getStatusVendor() {
		return statusVendor;
	}
	public void setStatusVendor(List<StatusVendorDao> statusVendor) {
		this.statusVendor = statusVendor;
	}
	public UserDao getVendor() {
		return vendor;
	}
	public void setVendor(UserDao vendor) {
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
	public UserDao getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}
	public UserDao getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(UserDao editedBy) {
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
