package com.bomber.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.bomber.repository.SafeNumberRepository;

public class SafeNumberScheduling {

	private final SafeNumberRepository SafeNumberRepository;

	@Autowired
	public SafeNumberScheduling(SafeNumberRepository safeNumberRepository) {
		super();
		SafeNumberRepository = safeNumberRepository;
	}

	@Scheduled(cron = "0 59 23 * * *", zone = "Indian/Maldives")
	private void clearAllNumbersFromSafeList() {
		SafeNumberRepository.deleteAll();
	}

}
