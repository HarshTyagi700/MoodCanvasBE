package com.harsh.dto;

import java.time.LocalDateTime;

public class CommentDTO {

	private String interactionId;
	private String useremail;
	private String interactedPinId;
	private LocalDateTime timestamp;
	private String comment;
	
	
	public CommentDTO(String interactionId, String useremail, String interactedPinId, String comment) {
		super();
		this.interactionId = interactionId;
		this.useremail = useremail;
		this.interactedPinId = interactedPinId;
		this.comment = comment;
	
	}

	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getInteractionId() {
		return interactionId;
	}

	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}

	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getInteractedPinId() {
		return interactedPinId;
	}
	public void setInteractedPinId(String interactedPinId) {
		this.interactedPinId = interactedPinId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}