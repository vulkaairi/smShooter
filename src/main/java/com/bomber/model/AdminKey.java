package com.bomber.model;

public enum AdminKey {

	ADMIN_KEY("adminsecretkey");

	private String key;

	AdminKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
}
