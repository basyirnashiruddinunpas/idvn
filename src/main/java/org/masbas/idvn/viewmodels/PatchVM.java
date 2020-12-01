package org.masbas.idvn.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PatchVM {

	private String id;
	@NotNull(message = "Catatan patch tidak boleh kosong.")
	@NotEmpty(message = "Catatan patch tidak boleh kosong.")
	private String catatanPatch;
	@NotNull(message = "Url patch tidak boleh kosong.")
	@NotEmpty(message = "Url patch tidak boleh kosong.")
	private String urlPatch;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCatatanPatch() {
		return catatanPatch;
	}
	public void setCatatanPatch(String catatanPatch) {
		this.catatanPatch = catatanPatch;
	}
	public String getUrlPatch() {
		return urlPatch;
	}
	public void setUrlPatch(String urlPatch) {
		this.urlPatch = urlPatch;
	}
	
	

}
