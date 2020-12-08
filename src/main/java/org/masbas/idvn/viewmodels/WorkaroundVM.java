package org.masbas.idvn.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkaroundVM {
	private String id;
	
	@NotNull(message = "Solusi tidak boleh kosong.")
	@NotEmpty(message = "Solusi tidak boleh kosong.")
	private String solution;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	
}
