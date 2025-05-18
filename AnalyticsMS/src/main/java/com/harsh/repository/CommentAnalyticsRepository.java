package com.harsh.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.harsh.entity.Comment;

public interface CommentAnalyticsRepository extends MongoRepository<Comment, String> {

	List<Comment> findByUseremail(String useremail);
	List<Comment> findByInteractedPinId(String interactedPinId);

//	@Query("{'useremail':?0,'interactedPinId':?1}")
//	List<Like> findPinInteracted(String useremail, String interactedPinId);
	List<Comment> findByUseremailAndInteractedPinId(String useremail,String interactedPinId);
}
