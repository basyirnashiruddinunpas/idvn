package org.masbas.idvn.viewmodels;

import java.time.ZonedDateTime;
import java.util.List;

import org.masbas.idvn.models.StatusVendor;
import org.masbas.idvn.models.User;
import org.masbas.idvn.models.Workaround;
import org.springframework.data.annotation.Id;

public class LaporanDto {
	@Id
	private String id;
	
	private String overview;
	private String productAffected;
	private String description;
	private String impact;
	private List<Workaround> workarounds;
	private List<StatusVendor> statusVendor;
	private User vendor;	
	private String vendorStr;
	private String references;
	private String vectorString;
	private String status;
	private User createdBy;
	private User editedBy;
	private ZonedDateTime createdTimeStamp;
	private ZonedDateTime updatedTimeStamp;
	
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
	public List<Workaround> getWorkarounds() {
		return workarounds;
	}
	public void setWorkarounds(List<Workaround> workarounds) {
		this.workarounds = workarounds;
	}
	public List<StatusVendor> getStatusVendor() {
		return statusVendor;
	}
	public void setStatusVendor(List<StatusVendor> statusVendor) {
		this.statusVendor = statusVendor;
	}
	public User getVendor() {
		return vendor;
	}
	public void setVendor(User vendor) {
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
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(User editedBy) {
		this.editedBy = editedBy;
	}
	public ZonedDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(ZonedDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public ZonedDateTime getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(ZonedDateTime updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	public String getVendorStr() {
		return vendorStr;
	}
	public void setVendorStr(String vendorStr) {
		this.vendorStr = vendorStr;
	}
	
	
}
