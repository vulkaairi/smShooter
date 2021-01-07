package com.bomber.validations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import com.bomber.model.Message;

@Component
public class ValidateNumber {

	public boolean validate(String number, Message message) {
		message.setTimeStamp(LocalDateTime.now());

		if (number.startsWith("8") || number.startsWith("7") || number.startsWith("9")) {
			if (number.length() == 10) {
				message.setMessage("bombing request accepted ;)");
			} else {
				message.setMessage("invalid number");
				return false;
			}
		} else {
			message.setMessage("only indian numbers allowed");
			return false;
		}
		return true;
	}

}
