package com.harsh.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="LikeAnalytics")
public class Like {

	  @Id
			private String id;
			private String useremail;
			private String interactedPinId;
			private LocalDateTime timestamp;
			private boolean liked;
			
			
			
			public Like() {
				super();
			}

			public Like( String useremail, String interactedPinId, LocalDateTime timestamp,
					boolean liked) {
				super();
				this.useremail = useremail;
				this.interactedPinId = interactedPinId;
				this.timestamp = timestamp;
				this.liked = liked;
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

			public LocalDateTime getTimestamp() {
				return timestamp;
			}

			public void setTimestamp(LocalDateTime timestamp) {
				this.timestamp = timestamp;
			}

			public boolean getLiked() {
				return liked;
			}

			public void setLiked(boolean liked) {
				this.liked = liked;
			}
			
			
}
