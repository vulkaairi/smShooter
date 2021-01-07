package com.bomber.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DailyBombingDetails {

	@Id
	private String id;
	private LocalDateTime l;
	private String number;
	private String ipAddress;
	private int todaysTotal;

	public DailyBombingDetails() {
	}

	public DailyBombingDetails(String id, LocalDateTime l, String number, String ipAddress, int todaysTotal) {
		super();
		this.id = id;
		this.l = l;
		this.number = number;
		this.ipAddress = ipAddress;
		this.todaysTotal = todaysTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getL() {
		return l;
	}

	public void setL(LocalDateTime l) {
		this.l = l;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getTodaysTotal() {
		return todaysTotal;
	}

	public void setTodaysTotal(int todaysTotal) {
		this.todaysTotal = todaysTotal;
	}

}
