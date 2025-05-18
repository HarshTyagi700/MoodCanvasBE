package com.harsh.dto;

import java.time.LocalDateTime;

public class LikeDTO {

	private String interactionId;
	private String useremail;
	private String interactedPinId;
	private LocalDateTime timestamp;
	private boolean liked;
	
	
	
	public LikeDTO(String interactionId, String useremail, String interactedPinId, LocalDateTime timestamp,
			boolean liked) {
		super();
		this.interactionId = interactionId;
		this.useremail = useremail;
		this.interactedPinId = interactedPinId;
		this.timestamp = timestamp;
		this.liked = liked;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	
	
}
