package com.harsh.entity;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pins")
public class Pin {
	   @Id
	    private String id;
	    
	    private String title;
	        
	    private Binary image;
	    
	    private String about;
	    
	    private String userEmail;
	    
	    private int likeCount;
	    
	    private List<String> comments;

	    
	    
		public Pin() {
			super();
		}

		public Pin(String id, String title, Binary image, String about, String userEmail, int likeCount,
				List<String> comments) {
			super();
			this.id = id;
			this.title = title;
			this.image = image;
			this.about = about;
			this.userEmail = userEmail;
			this.likeCount = likeCount;
			this.comments = comments;
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

		public Binary getImage() {
			return image;
		}

		public void setImage(Binary image) {
			this.image = image;
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

		public int getLikeCount() {
			return likeCount;
		}

		public void setLikeCount(int likeCount) {
			this.likeCount = likeCount;
		}

		public List<String> getComments() {
			return comments;
		}

		public void setComments(List<String> comments) {
			this.comments = comments;
		}
	    
	    
	    
	    
}
