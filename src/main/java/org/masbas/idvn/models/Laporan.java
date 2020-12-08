package org.masbas.idvn.models;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "laporan")
public class Laporan {
	
	@Id
	private String id;
	
	private String code;
	private String overview;
	private String productAffected;
	private String description;
	private String impact;
	private List<Workaround> workarounds;
	private List<StatusVendor> statusVendor;
	private Patch patch;
	
	@DBRef
	private User vendor;	
	private String references;
	private String vectorString;
	private String status;
	@DBRef
	private User createdBy;
	@DBRef
	private User editedBy;
	private Date createdTimeStamp;
	private Date updatedTimeStamp;
	
}
