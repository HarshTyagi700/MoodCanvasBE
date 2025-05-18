package com.harsh.dto;

import java.util.Set;

public class PinBoardDTO {

	
	private String pinBoardId;
	private String pinBoardName;
	private String useremail;
	private Set<String> pinIdList;
	
	public PinBoardDTO() {
		super();
	}
	

	public PinBoardDTO(String pinBoardName, String useremail, Set<String> pinIdList) {
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
