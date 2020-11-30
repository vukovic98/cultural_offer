package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public Comment save(Comment c) {
		return this.commentRepository.save(c);
	}
	
	public ArrayList<Comment> findAll() {
		return (ArrayList<Comment>) this.commentRepository.findAll();
	}
	
	public Comment findById(long id) {
		Optional<Comment> foundOptional =  this.commentRepository.findById(id);
		
		if(foundOptional.isPresent()) {
			Comment found = foundOptional.get();
			return found;
		}
		else
			return null;
	}
	
	public boolean delete(Comment c) {
		boolean exists = this.commentRepository.existsById(c.getCommentId());
		
		if(exists)
			this.commentRepository.delete(c);
		
		return exists;
	}
	
	public boolean deleteById(long id) {
		boolean exists = this.commentRepository.existsById(id);
		
		if(exists)
			this.commentRepository.deleteById(id);
		
		return exists;
	}
	
	public Comment update(Comment changedComment, long id) {
		Optional<Comment> oldComment = this.commentRepository.findById(id);
		
		if(oldComment.isPresent()) {
			Comment found = oldComment.get();
			
			found.setContent(changedComment.getContent());
			found.setImages(changedComment.getImages());
			
			return this.commentRepository.save(found);
		} else {
			return null;
		}
	}
}
