package com.harsh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harsh.dto.UserDTO;
import com.harsh.entity.User;
import com.harsh.exception.PinterestException;
import com.harsh.repository.UserRepository;
import com.harsh.utility.JwtUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passEnco ;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public String registerUser(UserDTO userDTO) throws PinterestException{
		
		//Check if user already exists or not
		Optional<User> userOp= userRepo.findByEmailId(userDTO.getEmailId());
		if(userOp.isPresent())
			throw new PinterestException("UserService.EMAIL_ALREADY_REGISTERED");

		//Checking username already taken or not
		userOp=userRepo.findById(userDTO.getUsername().toLowerCase());
		if(userOp.isPresent())
			throw new PinterestException("UserService.USERNAME_ALREADY_EXISTS");
		
		//Now we can register this user
		User user= new User(userDTO.getUsername().toLowerCase(), 
				userDTO.getEmailId().toLowerCase(), passEnco.encode(userDTO.getPassword()) ,
				userDTO.getFirstName(), userDTO.getLastName());
		
		userRepo.save(user);
		
		return user.getUsername();
	}
	
	public String authenticateUser(String id,String password) throws PinterestException{
		User user;
		//Finding By Email
		if(id.contains("@")) {
			user=userRepo.findByEmailId(id.toLowerCase()).orElseThrow(() -> new PinterestException("UserService.EMAIL_INVALID"));
		}
		//Else Finding using username
		else {
			 user=userRepo.findById(id.toLowerCase()).orElseThrow(() -> new PinterestException("UserService.USERNAME_INVALID"));
		}
		
		//Checking if password is matching with the one that is stored in DB
		if(passEnco.matches(password, user.getPassword()) ) {
			return  jwtUtil.generateToken(id);
		}
		else {
			throw new PinterestException("UserService.INCORRECT_PASSWORD");
		}
		
	}
	
	public UserDTO fetchUser(String id) throws PinterestException{
		User user;
		if(id.contains("@")) {
			user=userRepo.findByEmailId(id.toLowerCase()).orElseThrow(() -> new PinterestException("UserService.EMAIL_INVALID"));
		}
		//Else Finding using username
		else {
			 user=userRepo.findById(id.toLowerCase()).orElseThrow(() -> new PinterestException("UserService.USERNAME_INVALID"));
		}
		return new UserDTO(user.getUsername(),user.getEmailId(),user.getFirstName(),user.getLastName());
	}
	
}
