package org.masbas.idvn.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "try")
public class BaseModel {
	@Id
	String id;
	String name;
	String desc;
	Double price;
	String img;
	
	public BaseModel() {
		
	}
	
	public BaseModel(String id, String name, String desc, Double price, String img) {
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.img = img;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
