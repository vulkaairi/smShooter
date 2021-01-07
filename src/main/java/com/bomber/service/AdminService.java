package com.bomber.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bomber.model.AdminApiKey;
import com.bomber.model.AdminKey;
import com.bomber.repository.AdminApiKeyRepository;

@Service
public class AdminService {

	private final AdminApiKeyRepository adminApiKeyRepository;

	@Autowired
	public AdminService(AdminApiKeyRepository adminApiKeyRepository) {
		super();
		this.adminApiKeyRepository = adminApiKeyRepository;
	}

	public ResponseEntity<String> newAdminKey(String name, String adminKey) {
		if (name == null || name.equals("")) {
			return new ResponseEntity<String>("name error", HttpStatus.BAD_REQUEST);
		} else if (adminKey == null || adminKey.equals("") || !adminKey.equals(AdminKey.ADMIN_KEY.getKey())) {
			return new ResponseEntity<String>("admin_key error", HttpStatus.BAD_REQUEST);
		}

		String newApiKey = UUID.randomUUID().toString();
		adminApiKeyRepository.save(new AdminApiKey(UUID.randomUUID().toString(), newApiKey, name, 0));
		return new ResponseEntity<String>("your new api key is generated successfully please save it : " + newApiKey,
				HttpStatus.BAD_REQUEST);

	}

}
