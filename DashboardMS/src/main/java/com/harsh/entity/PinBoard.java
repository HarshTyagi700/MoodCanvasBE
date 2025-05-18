package com.harsh.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="PinBoard")
public class PinBoard {

	@Id
	private String pinBoardId;
	private String pinBoardName;
	private String useremail;
	private Set<String> pinIdList;
	
	public PinBoard() {
		super();
	}

	public PinBoard(String pinBoardName, String useremail, Set<String> pinIdList) {
		super();
		this.pinBoardName = pinBoardName;
		this.useremail = useremail;
		this.pinIdList = pinIdList;
	}

	public String getPinBoardId() {
		return pinBoardId;
	}

	public void setPinBoardId(String pinBoardId) {
		this.pinBoardId = pinBoardId;
	}

	public String getPinBoardName() {
		return pinBoardName;
	}

	public void setPinBoardName(String pinBoardName) {
		this.pinBoardName = pinBoardName;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public Set<String> getPinIdList() {
		return pinIdList;
	}

	public void setPinIdList(Set<String> pinIdList) {
		this.pinIdList = pinIdList;
	}
	
	
	
	
}
