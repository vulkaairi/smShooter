package com.bomber.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bomber.model.Message;
import com.bomber.service.BomberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Boombing-controller")
@RestController
@RequestMapping("/api/v1")
public class BombingController {

	private final BomberService bomberService;

	@Autowired
	public BombingController(BomberService bomberService) {
		this.bomberService = bomberService;
	}

	@ApiOperation(value = "pass only indian number as url variable \n Can bomb a number only twice a day as normal user. (admin api keys have no restrictions)  \n if You have an admin api key you can pass it as request parameter (/api/v1/bomb/{IndianNumber}/?key=yourAdminApiKey). If you need a personal api key for unlimited bombing then contact the developer")
	@CrossOrigin
	@RequestMapping(value = "/bomb/{IndianNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> bombSms(@PathVariable("IndianNumber") String number, HttpServletRequest request) {
		Message msg = new Message();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return bomberService.bomb(number.trim(), msg, ipAddress, request.getParameter("key"));
	}
}
