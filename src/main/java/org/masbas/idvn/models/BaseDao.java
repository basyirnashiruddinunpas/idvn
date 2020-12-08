package org.masbas.idvn.models;

import java.security.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BaseDao {
	@Id
	protected String id;
	protected Timestamp timestamp;
	protected Timestamp updatedTimstamp;
	protected String status;
	
	public BaseDao(String id, Timestamp timestamp, Timestamp updatedTimstamp, String status) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.updatedTimstamp = updatedTimstamp;
		this.status = status;
	}
	
}
