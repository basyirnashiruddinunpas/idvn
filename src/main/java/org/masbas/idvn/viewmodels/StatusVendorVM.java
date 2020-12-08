package org.masbas.idvn.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusVendorVM {
	private String id;
	
	@NotNull(message = "Status Vendor tidak boleh kosong.")
	@NotEmpty(message = "Status Vendor tidak boleh kosong.")
	private String statusVendor;
	
	@NotNull(message = "Catatan Vendor tidak boleh kosong.")
	@NotEmpty(message = "Catatan Vendor tidak boleh kosong.")
	private String catatanVendor;
	
	private Date createdTimestamp;
	private Date updatedTimestamp;
	
}
