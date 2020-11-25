package org.masbas.idvn.models;

import java.security.Timestamp;

import org.springframework.data.annotation.Id;

public class BaseDao {
	@Id
	protected String id;
	protected Timestamp timestamp;
	protected Timestamp updatedTimstamp;
	protected String status;
	
	public BaseDao() {
		
	}

	public BaseDao(String id, Timestamp timestamp, Timestamp updatedTimstamp, String status) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.updatedTimstamp = updatedTimstamp;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getUpdatedTimstamp() {
		return updatedTimstamp;
	}

	public void setUpdatedTimstamp(Timestamp updatedTimstamp) {
		this.updatedTimstamp = updatedTimstamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
