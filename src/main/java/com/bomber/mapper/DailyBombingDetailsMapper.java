package com.bomber.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bomber.model.DailyBombingDetails;

@Component
public class DailyBombingDetailsMapper {

	public DailyBombingDetails mapRequestToMessage(String number, String ipAddress) {
		DailyBombingDetails dailyBombingDetails = new DailyBombingDetails();
		dailyBombingDetails.setId(geneRateUUID());
		dailyBombingDetails.setIpAddress(ipAddress);
		dailyBombingDetails.setNumber(number);
		dailyBombingDetails.setL(LocalDateTime.now());
		dailyBombingDetails.setTodaysTotal(1);
		return dailyBombingDetails;
	}

	private String geneRateUUID() {
		return UUID.randomUUID().toString();
	}
}
