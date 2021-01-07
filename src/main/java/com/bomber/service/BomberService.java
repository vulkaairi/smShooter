package com.bomber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bomber.mapper.DailyBombingDetailsMapper;
import com.bomber.model.AdminApiKey;
import com.bomber.model.DailyBombingDetails;
import com.bomber.model.Message;
import com.bomber.outer.Bomb;
import com.bomber.repository.AdminApiKeyRepository;
import com.bomber.repository.DailyBombingDetailsRepository;
import com.bomber.repository.SafeNumberRepository;
import com.bomber.validations.ValidateNumber;

@Service
public class BomberService {

	private final ValidateNumber validate;
	private final DailyBombingDetailsMapper bombingDetailsMapper;
	private final DailyBombingDetailsRepository bombingDetailsRepo;
	private final Bomb bomb;
	private final AdminApiKeyRepository adminApiKeyRepository;
	private final SafeNumberRepository safeNumberRepository;

	@Autowired
	public BomberService(ValidateNumber validate, DailyBombingDetailsMapper bombingDetailsMapper,
			DailyBombingDetailsRepository mailRepo, Bomb bomb, AdminApiKeyRepository adminApiKeyRepository,
			SafeNumberRepository safeNumberRepository) {
		super();
		this.validate = validate;
		this.bombingDetailsMapper = bombingDetailsMapper;
		this.bombingDetailsRepo = mailRepo;
		this.bomb = bomb;
		this.adminApiKeyRepository = adminApiKeyRepository;
		this.safeNumberRepository = safeNumberRepository;
	}

	public ResponseEntity<Message> bomb(String number, Message message, String ipAddress, String key) {

		boolean isValid = validate.validate(number.trim(), message);

		if (isValid) {

			AdminApiKey findByApiKey = null;
			if (key != null && !key.equals("")) {
				findByApiKey = adminApiKeyRepository.findByApiKey(key.trim());
			}
			if (findByApiKey != null) {
				System.out.println("you are admin");
				findByApiKey.setCount(findByApiKey.getCount() + 1);
				adminApiKeyRepository.save(findByApiKey);
				DailyBombingDetails dBombing = bombingDetailsRepo.findByNumber(number);
				if (dBombing == null) {
					bombingDetailsRepo.save(bombingDetailsMapper.mapRequestToMessage(number, ipAddress));
					bomb.bomb(number);
				} else {
					dBombing.setTodaysTotal(dBombing.getTodaysTotal() + 1);
					bombingDetailsRepo.save(dBombing);
					bomb.bomb(number);
				}

			} else {

				System.out.println("you are normal user");
				if (!safeNumberRepository.existsByNumber(number.trim())) {
					DailyBombingDetails dBombing = bombingDetailsRepo.findByNumber(number);
					if (dBombing == null) {
						bombingDetailsRepo.save(bombingDetailsMapper.mapRequestToMessage(number, ipAddress));
						bomb.bomb(number);
					} else if (dBombing.getTodaysTotal() < 2) {
						dBombing.setTodaysTotal(dBombing.getTodaysTotal() + 1);
						bombingDetailsRepo.save(dBombing);
						bomb.bomb(number);
					} else {
						message.setMessage("daily limit to bomb this number is reached..try tommorrow");
					}
				} else {
					message.setMessage("number is safe for today, try tommorrow ;)");
				}

			}

		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
