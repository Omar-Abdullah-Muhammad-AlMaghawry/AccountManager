package com.zfinance.services.rng;

import java.util.UUID;

import org.springframework.stereotype.Service;


@Service
public class RngServiceImplementation implements RngService {

	@Override
	public String getRandomCodeOfSize8() {
        String randomId = UUID.randomUUID().toString();
        return randomId.substring(0, 8);	    
	}

}
