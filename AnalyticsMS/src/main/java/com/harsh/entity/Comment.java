package com.harsh.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CommentAnalytics")
public class Comment {
	   @Id
		private String id;
		private String useremail;
		private String interactedPinId;
		private LocalDateTime timestamp;
		private String comment;
		

		public Comment( String useremail, String interactedPinId, String comment,LocalDateTime timestamp) {
			super();
			this.useremail = useremail;
			this.interactedPinId = interactedPinId;
			this.comment = comment;
			this.timestamp=timestamp;

		}

		
		
		public LocalDateTime getTimestamp() {
			return timestamp;
		}



		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}



		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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
