package com.bomber.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.bomber.repository.DailyBombingDetailsRepository;

public class dailyBombingDataScheduling {

	private final DailyBombingDetailsRepository dailyBombingDetailsRepository;
	
	
	@Autowired
	public dailyBombingDataScheduling(DailyBombingDetailsRepository dailyBombingDetailsRepository) {
		super();
		this.dailyBombingDetailsRepository = dailyBombingDetailsRepository;
	}



	@Scheduled(cron = "0 40 23 * * *", zone = "Indian/Maldives")
	public void setValuesToZero() {
		dailyBombingDetailsRepository.deleteAll();
	}
	
}
