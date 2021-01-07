package com.bomber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bomber.model.DailyBombingDetails;

@Repository
public interface DailyBombingDetailsRepository extends JpaRepository<DailyBombingDetails, String> {

	DailyBombingDetails  findByNumber(String number);
	

}
