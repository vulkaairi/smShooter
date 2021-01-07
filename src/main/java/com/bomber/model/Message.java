package com.bomber.model;

import java.time.LocalDateTime;


public class Message {

	private LocalDateTime timeStamp;
	private String message;
	
	public Message() {
	}
	
	
	
	public Message(LocalDateTime timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}



	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
