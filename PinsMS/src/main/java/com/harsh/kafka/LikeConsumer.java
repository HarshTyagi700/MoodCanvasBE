package com.harsh.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.harsh.dto.LikeEvent;
import com.harsh.entity.Pin;
import com.harsh.exception.PinterestException;
import com.harsh.repository.PinRepository;

@Service
public class LikeConsumer {

	
	private static final Logger LOGGER= LoggerFactory.getLogger(LikeConsumer.class);
	
	@Autowired
	PinRepository pinsRepo;
	
	@KafkaListener(topics="like_topics",groupId = "pins")
	public void consume(LikeEvent event) throws PinterestException {
		
		LOGGER.info(String.format("Like Event Received in Pins Service => %s", event.toString()));
		
		try {
			Pin pin= pinsRepo.findById(event.getPinid()).orElseThrow( ()-> new PinterestException("PinID.Invalid") );
			if(event.getLike())
				pin.setLikeCount(pin.getLikeCount()+1);
			else pin.setLikeCount(pin.getLikeCount()-1);
			pinsRepo.save(pin);
		}
		catch(PinterestException e) {
			LOGGER.info(String.format("Pinterest Exception occured .. Proceeding to next message event => %s", event.toString()));
		}
		
		
		
	}
	
}
