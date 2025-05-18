package com.harsh.dto;

public class UserDTO {

	private String username;
	private String useremail;
	private String firstName;
	private String lastName;
	
	
	
	
	public UserDTO() {
		super();
	}
	public UserDTO(String username, String useremail, String firstName, String lastName) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
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
