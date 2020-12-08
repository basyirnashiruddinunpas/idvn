package org.masbas.idvn.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "workaround")
public class Workaround {
	@Id
	private String id;
	private String solution;
	
	private Date createdTimestamp;
	private Date updatedTimestamp;
	
	
}
