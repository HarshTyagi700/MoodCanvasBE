package com.harsh.dto;

public class UserDTO {

	private String username;
	private String emailId;
	private String firstName;
	private String lastName;
	
	public UserDTO() {
		super();
	}

	public UserDTO(String username, String emailId, String firstName, String lastName) {
		super();
		this.username = username;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
