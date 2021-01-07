package com.bomber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bomber.service.SafeNumberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Safe-Number-Controller")
@RequestMapping("/api/v1")
public class SafeNumberController {

	private final SafeNumberService safeNumberService;

	@Autowired
	public SafeNumberController(SafeNumberService safeNumberService) {
		super();
		this.safeNumberService = safeNumberService;
	}

	
	@ApiOperation(value = "A number added to safe number's list cannot be bombed for that day.Safe number's list get cleared everyday.")
	@CrossOrigin
	@RequestMapping(value = "/safelist/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToSafeList(@PathVariable("number") String number) {
		return safeNumberService.addNumber(number);
	}

}
