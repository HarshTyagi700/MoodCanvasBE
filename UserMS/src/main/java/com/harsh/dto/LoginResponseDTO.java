package com.harsh.dto;

public class LoginResponseDTO {
    private String token;
    private String username;
    private String emailId;

    // Constructors
    public LoginResponseDTO() {}

    public LoginResponseDTO(String token, String username, String emailId) {
        this.token = token;
        this.username = username;
        this.emailId = emailId;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
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
}
