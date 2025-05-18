package com.harsh.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.dto.LikeDTO;
import com.harsh.dto.UserDTO;
import com.harsh.entity.Comment;
import com.harsh.exception.PinterestException;
import com.harsh.service.CommentService;
import com.harsh.service.LikeService;

@RestController
@RequestMapping(value= "/analytics-api")
public class AnalyticsAPI {
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value="/updateLike")
	public ResponseEntity<String> updateLike(@RequestBody LikeDTO likeDTO ){
		

		return new ResponseEntity<> (likeService.updateLike(likeDTO.getUseremail(), likeDTO.getInteractedPinId(), likeDTO.isLiked()),HttpStatus.OK);
	}
	
	@PostMapping(value="addComment")
	public ResponseEntity<String> addComment(@RequestBody Comment comment ){
		return new ResponseEntity<> (commentService.addComment(comment.getInteractedPinId(),comment.getUseremail(),LocalDateTime.now(),comment.getComment()),HttpStatus.OK);
	}
	
	@GetMapping(value="/comments/pinid={pinId}")
	public ResponseEntity<List<Comment>> getCommentsOfPinId(@PathVariable String pinId) throws PinterestException{
		return new ResponseEntity<>(commentService.getAllCommentsForPinId(pinId),HttpStatus.OK);
	}
	@PostMapping(value="/getComments/")
	public ResponseEntity<List<Comment>> getCommentsByUser(@RequestBody UserDTO userDTO) throws PinterestException{
		
		return new ResponseEntity<>(commentService.getAllCommentsByUseremail(userDTO.getUseremail()),HttpStatus.OK);
	}
	
	@PostMapping(value="/hasLiked")
	public ResponseEntity<Boolean> hasUserLikedThePost(@RequestParam String useremail,@RequestParam String pinId){
		return new ResponseEntity<>(likeService.hasLiked(useremail,pinId),HttpStatus.OK);
	}

}
