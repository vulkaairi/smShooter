package com.bomber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminApiKey {

	@Id
	private String id;
	private String apiKey;
	private String name;
	private int count;

	public AdminApiKey() {
	}

	public AdminApiKey(String id, String apiKey, String name, int count) {
		super();
		this.id = id;
		this.apiKey = apiKey;
		this.name = name;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
