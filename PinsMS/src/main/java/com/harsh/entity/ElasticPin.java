package com.harsh.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Document(indexName = "pin")
public class ElasticPin {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	@Field(type = FieldType.Text)
	private String title;
	@Field(type = FieldType.Text)
	private String about;  
	@Field(type = FieldType.Text)
	private String userEmail;
	
	public ElasticPin() {
		
	}
	
	 public ElasticPin(String id, String title, String about, String userEmail) {
	        this.id = id;
	        this.title = title;
	        this.about = about;
	        this.userEmail = userEmail;
	    }
	 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
}
