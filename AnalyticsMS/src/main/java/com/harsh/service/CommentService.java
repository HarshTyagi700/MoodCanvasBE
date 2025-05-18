package com.harsh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harsh.entity.Comment;
import com.harsh.exception.PinterestException;
import com.harsh.repository.CommentAnalyticsRepository;






@Service
@Transactional
public class CommentService {

	
	@Autowired
	private CommentAnalyticsRepository commentRepo;
	
	public String addComment(String interactedPinId,String useremail, LocalDateTime timestamp, String cmnt) {
		
		Comment comment = new Comment(useremail,interactedPinId,cmnt,timestamp); 
	
		String commentId=commentRepo.insert(comment).getId();
		return "New Comment added with commentId :" + commentId;
	}
	
	public List<Comment> getAllCommentsForPinId(String pinId) throws PinterestException {
		
		List<Comment> commentList=commentRepo.findByInteractedPinId(pinId);
		if(commentList.isEmpty())
			throw new PinterestException("CommentList.Empty");
		return commentList;
		
	}
	
	public List<Comment> getAllCommentsByUseremail(String useremail) throws PinterestException{
		List<Comment> commentList=commentRepo.findByUseremail(useremail);
		
		if(commentList.isEmpty())
			throw new PinterestException("CommentList.EmptyByUser");
		return commentList;
	}
	
	
	
}
