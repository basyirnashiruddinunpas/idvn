package org.masbas.idvn.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "try")
public class BasedModel {
	@Id
	String id;
	String name;
	String desc;
	Double price;
	String img;
	
	public BasedModel(String id, String name, String desc, Double price, String img) {
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.img = img;
	}
	
}
