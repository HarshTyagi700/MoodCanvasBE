package com.harsh.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.harsh.entity.Like;

public interface LikeAnalyticsRepository extends MongoRepository<Like, String> {
	
	List<Like> findByUseremail(String useremail);
	List<Like> findByInteractedPinId(String interactedPinId);
	
	@Query("{'useremail':?0,'interactedPinId':?1}")
	List<Like> findPinInteracted(String useremail, String interactedPinId);
	

}
