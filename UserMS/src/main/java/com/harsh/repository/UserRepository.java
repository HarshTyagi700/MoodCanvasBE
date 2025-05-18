package com.harsh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.harsh.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailId(String emailId);
    Optional<User> findById(String username);
}
