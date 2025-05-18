package com.harsh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harsh.dto.LikeEvent;
import com.harsh.entity.Like;
import com.harsh.repository.CommentAnalyticsRepository;
import com.harsh.repository.LikeAnalyticsRepository;

@Service
@Transactional
public class LikeService {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(LikeService.class);
	
	@Autowired
	LikeAnalyticsRepository likeRepo;
	
	@Autowired
	CommentAnalyticsRepository commentRepo;
	
	private NewTopic newTopic;
	
	private KafkaTemplate<String, LikeEvent> kafkaTemplate;
	
	
	public LikeService(NewTopic newTopic, KafkaTemplate<String, LikeEvent> kafkaTemplate) {
		super();
		this.newTopic = newTopic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(LikeEvent event) {
		
		LOGGER.info(String.format("Profile Event => %s", event.toString()));
		Message<LikeEvent> message = MessageBuilder
		.withPayload(event)
		.setHeader(KafkaHeaders.TOPIC, newTopic.name())
		.build();
		
		kafkaTemplate.send(message);

	}
	
	public boolean hasLiked(String useremail,String interactedPinId) {
		List<Like> likeList=likeRepo.findByUseremail(useremail);
		return likeList.stream().anyMatch(like -> like.getInteractedPinId().equals(interactedPinId));
	}

	public String updateLike(String useremail, String interactedPinId ,boolean isLiked ) {
		List<Like> likeList=likeRepo.findPinInteracted(useremail,interactedPinId);
		//No previous interaction with the post
		if(likeList.isEmpty()) {
			
			if(isLiked==false)
				return "Invalid API call !!";
			//Creating an Entity and storing in DB after that we'll call the producer
			Like like=new Like(useremail,interactedPinId,LocalDateTime.now(),isLiked);
			likeRepo.insert(like);
			
		}
		else {
			Like like= likeList.get(0);
			if(like.getLiked()==isLiked) {
				return "No Change in Like Status !!";
			}
					like.setLiked(isLiked);
					likeRepo.save(like);
		}
				//Sending Event
				LikeEvent likeEvent = new LikeEvent();
				likeEvent.setLike(isLiked);
				likeEvent.setPinid(interactedPinId);
				likeEvent.setUseremail(useremail);
				this.sendMessage(likeEvent);
		
		return "Like Status Updated";
		
	}

	

}
