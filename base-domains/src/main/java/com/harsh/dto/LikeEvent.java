package com.harsh.dto;

public class LikeEvent {
	
	private boolean like;
	private String useremail;
	private String pinid;
	public LikeEvent() {
		super();
	}
	public LikeEvent(boolean like, String useremail, String pinid) {
		super();
		this.like = like;
		this.useremail = useremail;
		this.pinid = pinid;
	}
	public boolean getLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getPinid() {
		return pinid;
	}
	public void setPinid(String pinid) {
		this.pinid = pinid;
	}
	
}
