package org.masbas.idvn.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patch")
public class Patch {
	@Id
	private String id;
	private String catatanPatch;
	private String urlPatch;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
