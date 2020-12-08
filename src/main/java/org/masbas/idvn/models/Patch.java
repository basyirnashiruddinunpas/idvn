package org.masbas.idvn.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "patch")
public class Patch {
	@Id
	private String id;
	private String catatanPatch;
	private String urlPatch;
	private Date createdTimestamp;
	private Date updatedTimestamp;

}
