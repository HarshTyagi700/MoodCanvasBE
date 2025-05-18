package com.harsh.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.entity.Pin;

public interface PinRepository extends MongoRepository<Pin, String> {

	List<Pin> findAllByUserEmail(String email);

}
