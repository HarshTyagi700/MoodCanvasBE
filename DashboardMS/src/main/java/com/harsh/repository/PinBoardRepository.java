package com.harsh.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.entity.PinBoard;

public interface PinBoardRepository extends MongoRepository<PinBoard, String> {

	List<PinBoard> findAllByUseremail(String email);
	
	Optional<PinBoard> findByUseremailAndPinBoardName(String useremail,String pinBoardName);

}
