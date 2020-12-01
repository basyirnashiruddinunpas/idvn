package org.masbas.idvn.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StatusVendorVM {
	private String id;
	
	@NotNull(message = "Status Vendor tidak boleh kosong.")
	@NotEmpty(message = "Status Vendor tidak boleh kosong.")
	private String statusVendor;
	
	@NotNull(message = "Catatan Vendor tidak boleh kosong.")
	@NotEmpty(message = "Catatan Vendor tidak boleh kosong.")
	private String catatanVendor;
	
	private Date createdTimestamp;
	private Date updatedTimestamp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatusVendor() {
		return statusVendor;
	}
	public void setStatusVendor(String statusVendor) {
		this.statusVendor = statusVendor;
	}
	public String getCatatanVendor() {
		return catatanVendor;
	}
	public void setCatatanVendor(String catatanVendor) {
		this.catatanVendor = catatanVendor;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
}
