package com.bomber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bomber.model.AdminApiKey;

@Repository
public interface AdminApiKeyRepository extends JpaRepository<AdminApiKey, String> {

	AdminApiKey findByApiKey(String key);

}
