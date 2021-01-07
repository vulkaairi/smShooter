package com.bomber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bomber.model.SafeNumber;

public interface SafeNumberRepository extends JpaRepository<SafeNumber, String> {

	boolean existsByNumber(String number);

}
