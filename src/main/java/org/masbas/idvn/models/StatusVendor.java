package org.masbas.idvn.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "status_vendor")
public class StatusVendor {
	@Id
	private String id;
	private String statusVendor;
	private String catatanVendor;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	
	
}
