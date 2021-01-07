package com.bomber.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bomber.model.Message;
import com.bomber.model.SafeNumber;
import com.bomber.repository.SafeNumberRepository;
import com.bomber.validations.ValidateNumber;

@Service
public class SafeNumberService {

	private final SafeNumberRepository safeNumberRepository;
	private final ValidateNumber validateNumber;
	
	
	
	@Autowired
	public SafeNumberService(SafeNumberRepository safeNumberRepository, ValidateNumber validateNumber) {
		super();
		this.safeNumberRepository = safeNumberRepository;
		this.validateNumber = validateNumber;
	}




	public ResponseEntity<String> addNumber(String number) {
		boolean isValid;
		Message msg = new Message();
		if(safeNumberRepository.existsByNumber(number)) {
			return new ResponseEntity<String>("Number already exist in safe list for today" ,HttpStatus.OK);
		}else if ((isValid = validateNumber.validate(number, msg)) == true){
			safeNumberRepository.save(new SafeNumber(UUID.randomUUID().toString(), number.trim()));
			return new ResponseEntity<String>("successfully added number to safe list for today" ,HttpStatus.OK);			
		}
		return new ResponseEntity<String>(msg.getMessage(), HttpStatus.OK);
	}

	
	
}
