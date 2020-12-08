package org.masbas.idvn.viewmodels;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.masbas.idvn.models.StatusVendor;
import org.masbas.idvn.models.User;
import org.masbas.idvn.models.Workaround;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaporVM {
	@Id
	private String id;
	
	@NotNull(message = "Overview tidak boleh kosong.")
	@Size(min=10, max=100, message = "Overview harus diisi setidaknya 10 karakter dan tidak melebihi 100 karakter.")
	private String overview;
	
	@NotNull(message = "Product affected tidak boleh kosong.")
	@Size(min=10, max=200, message = "Product affected harus diisi setidaknya 10 karakter dan tidak melebihi 200 karakter.")
	private String productAffected;
	
	@NotNull(message = "Deskripsi kerentanan tidak boleh kosong.")
	@Size(min=10, message = "Deskripsi kerentnanan harus diisi setidaknya 10 karakter.")
	private String description;
	
	@NotNull(message = "Impact tidak boleh kosong.")
	@Size(min=10, message = "Impact harus diisi setidaknya 10 karakter.")
	private String impact;
	private List<Workaround> workarounds;
	private List<StatusVendor> statusVendor;
	private User vendor;	
	private String vendorStr;
	private String references;
	private String vectorString;
	private String status;
	private User createdBy;
	private User editedBy;
	private ZonedDateTime createdTimeStamp;
	private ZonedDateTime updatedTimeStamp;
	
}
