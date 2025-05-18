package com.harsh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.dto.LoginResponseDTO;
import com.harsh.dto.UserDTO;
import com.harsh.exception.PinterestException;
import com.harsh.service.UserService;

@RestController
@RequestMapping(value="/user-api")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private Environment environment;

	@PostMapping(value="/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO)
			throws PinterestException {
		String successMessage = userService.registerUser(userDTO);
		return new ResponseEntity<>(environment.getProperty("UserAPI.REGISTERED_SUCCESSFULLY") + successMessage ,HttpStatus.OK);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody UserDTO userDTO)
	        throws PinterestException {
	    
	    String id = (userDTO.getEmailId() == null || userDTO.getEmailId().isBlank()) 
	                  ? userDTO.getUsername() 
	                  : userDTO.getEmailId();
	    
	    String token = userService.authenticateUser(id, userDTO.getPassword());
	    UserDTO fetchedUser = userService.fetchUser(id); //

	    LoginResponseDTO response = new LoginResponseDTO(token, fetchedUser.getUsername(), fetchedUser.getEmailId());
	    
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="/fetch-user")
	public ResponseEntity<UserDTO> fetchUser(@RequestBody UserDTO userDTO)
			throws PinterestException {
		return new ResponseEntity<>( userService.fetchUser(userDTO.getEmailId()),HttpStatus.OK);
	}
	
}
