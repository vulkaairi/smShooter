package com.bomber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SafeNumber {

	@Id
	private String id;
	private String number;
	
	public SafeNumber() {
	}
	
	public SafeNumber(String id, String number) {
		super();
		this.id = id;
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
