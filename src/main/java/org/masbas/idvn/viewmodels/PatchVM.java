package org.masbas.idvn.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
	
}
